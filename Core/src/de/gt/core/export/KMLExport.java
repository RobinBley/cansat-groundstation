package de.gt.core.export;

import de.gt.api.export.Exporter;
import de.gt.api.gps.GPSKey;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Exports to KML
 * @author mhuisi
 */
public class KMLExport implements Exporter {

    private static final String KML_TEMPLATE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
        "  <Document>\n" +
        "    <name>Satellite flight path</name>\n" +
        "    <description>Shows the flight path of the satellite.</description>\n" +
        "    <Style id=\"yellowLineGreenPoly\">\n" +
        "      <LineStyle>\n" +
        "        <color>7f00ffff</color>\n" +
        "        <width>4</width>\n" +
        "      </LineStyle>\n" +
        "      <PolyStyle>\n" +
        "        <color>7f00ff00</color>\n" +
        "      </PolyStyle>\n" +
        "    </Style>\n" +
        "    <Placemark>\n" +
        "      <name>Flight path</name>\n" +
        "      <description>The actual path of the satellite</description>\n" +
        "      <styleUrl>#yellowLineGreenPoly</styleUrl>\n" +
        "      <LineString>\n" +
        "        <extrude>1</extrude>\n" +
        "        <tessellate>1</tessellate>\n" +
        "        <altitudeMode>absolute</altitudeMode>\n" +
        "        <coordinates>%s</coordinates>\n" +
        "      </LineString>\n" +
        "    </Placemark>\n" +
        "    <Style id=\"downArrowIcon\">\n" +
        "      <IconStyle>\n" +
        "        <Icon>\n" +
        "          <href>http://maps.google.com/mapfiles/dir_60.png</href>\n" +
        "        </Icon>\n" +
        "      </IconStyle>\n" +
        "    </Style>\n" +
        "    %s" +
        "  </Document>\n" +
        "</kml>\n";
    private static final String ANNOTATION_TEMPLATE = "<Placemark>\n" +
        "    <name></name>\n" +
        "    <description>%s</description>\n" +
        "    <styleUrl>#downArrowIcon</styleUrl>\n" +
        "    <Point>\n" +
        "      <extrude>1</extrude>\n" +
        "      <altitudeMode>relativeToGround</altitudeMode>\n" +
        "      <coordinates>%f,%f,%f</coordinates>\n" +
        "    </Point>\n" +
        "  </Placemark>\n";
    
    private final GPSKey k;
    
    public KMLExport(GPSKey k) {
        this.k = k;
    }
    
    @Override
    public boolean exportData(Map<String, List<Double>> data, File output) {
        List<Double> latitudes = data.get(k.getLatitudeKey());
        List<Double> longitudes = data.get(k.getLongitudeKey());
        List<Double> altitudes = data.get(k.getAltitudeKey());
        int latitudesSize = latitudes.size();
        boolean missingData = data.values().stream()
                .mapToInt(List::size)
                .anyMatch(s -> s != latitudesSize);
        if (missingData) {
            return false;
        }
        String pathCoords = IntStream.range(0, latitudesSize)
                .mapToObj(i -> String.format(Locale.US, "%f,%f,%f\n", 
                        latitudes.get(i), longitudes.get(i), altitudes.get(i)))
                .collect(Collectors.joining());
        Set<Entry<String, List<Double>>> entries = data.entrySet();
        String annotations = IntStream.range(0, latitudesSize)
                .mapToObj(i -> { 
                    String formattedData = entries.stream()
                                .map(e -> String.format("%s: %s\n", 
                                        e.getKey(), e.getValue().get(i)))
                                .collect(Collectors.joining());
                    return String.format(Locale.US, ANNOTATION_TEMPLATE, formattedData, 
                            latitudes.get(i), longitudes.get(i), altitudes.get(i));
                }).collect(Collectors.joining());
        try (BufferedWriter w = new BufferedWriter(new FileWriter(output))) {
            w.write(String.format(KML_TEMPLATE, pathCoords, annotations));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
}