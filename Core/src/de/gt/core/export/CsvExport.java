package de.gt.core.export;

import de.gt.api.export.Exporter;
import de.gt.api.input.data.DataUnit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Diese Klasse regelt das exportieren von Daten in eine CSV Datei.
 *
 * @author Robin
 */
public class CsvExport implements Exporter {

    @Override
    public boolean exportData(Map<String, List<DataUnit>> data, File output) {
        //Wenn das uebergebene File oder die uebergebene Map null ist, wird false zurueckgegeben.
        if (output == null || data == null) {
            return false;
        }
        try {
            //Es wird ein BufferedWriter erzeugund, um das uebergebene File zu beschreiben.
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));

            StringBuffer buffer = new StringBuffer();
            //Die Laenge der groessten DatenMenge der Map wird ermittelt.
            //DIe Keys der Map werden einem StringBuffer hunzugefuegt.
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
                    if (data.get(key).size() <= index) {
                        buffer.append("null");
                    } else if (data.get(key).get(index) == null) {
                        buffer.append("null");
                    } else {
                        buffer.append((String) data.get(key).get(index).getObjectValue().toString());
                    }
                    buffer.append(";");
                }
                writer.write(buffer.toString() + System.getProperty("line.separator"));
                index++;
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            Logger.getLogger(CsvExport.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

        return true;

    }
}
