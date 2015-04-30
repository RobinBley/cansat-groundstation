package de.gt.core.export;

import de.gt.api.export.Exporter;
import de.gt.api.gps.GPSKey;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 
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
        "  </Document>\n" +
        "</kml>";
    
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
        if (latitudesSize != longitudes.size() || latitudesSize != altitudes.size()) {
            return false;
        }
        StringBuilder b = new StringBuilder();
        IntStream.range(0, latitudesSize)
                .mapToObj(i -> String.format("%f,%f,%f\n", 
                        latitudes.get(i), longitudes.get(i), altitudes.get(i)))
                .forEach(b::append);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(output))) {
            w.write(String.format(KML_TEMPLATE, b.toString()));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
}
