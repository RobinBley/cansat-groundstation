package de.gt.core.export;

import de.gt.api.export.DataExporter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 * Diese Klasse regelt das Exportieren von Daten in eine CSV Datei.
 *
 * @author Robin
 */
@ServiceProvider(service = de.gt.api.export.Exporter.class)
public class CsvExport implements DataExporter {

    @Override
    public boolean exportData(Map<String, List<Double>> data, File output) {
        //Wenn das uebergebene File oder die uebergebene Map null ist, wird false zurueckgegeben.
        if (output == null || data == null) {
            return false;
        }
        //Es wird ein BufferedWriter erzeugund, um das uebergebene File zu beschreiben.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output));) {

            StringBuffer buffer = new StringBuffer();
            //Die Laenge der groessten DatenMenge der Map wird ermittelt.
            //Die Keys der Map werden einem StringBuffer hunzugefuegt.
            int maxSize = 0;
            for (String key : data.keySet()) {
                buffer.append(key + ";");
                if (data.get(key).size() > maxSize) {
                    maxSize = data.get(key).size();
                }
            }
            //Die Keys der Map werden in im CSV-Format in eine Datei geschrieben.
            writer.write(buffer.toString() + System.getProperty("line.separator"));
            //Die Werte der einzelnen Keys werden jeweuks unter den jeweiligen Keys in der CSV-Datei geschrieben.
            int index = 0;
            while (index < maxSize) {
                buffer = new StringBuffer();
                for (String key : data.keySet()) {
                    try {
                        buffer.append(data.get(key).get(index));
                    } catch (Exception e) {
                    }
                    buffer.append(";");
                }
                writer.write(buffer.toString() + System.getProperty("line.separator"));
                index++;
            }
        } catch (Exception e) {
            System.out.println("Fehler beim exportieren der Daten");
        }
        return true;
    }

    @Override
    public String getExporterName() {
        return "CSV";
    }

    @Override
    public String getFileExt() {
        return "csv";
    }
}
