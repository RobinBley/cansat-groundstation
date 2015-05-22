package de.gt.core.export;

import de.gt.api.export.Exporter;
import de.gt.api.log.Out;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 * Diese Klasse regelt das exportieren von Daten in eine formatierte Txt-Datei.
 *
 * @author Robin
 */
@ServiceProvider(service=de.gt.api.export.Exporter.class)
public class TxtExport implements Exporter {

    @Override
    public boolean exportData(Map<String, List<Double>> data, File output) {
        //Wenn das uebergebene File oder die uebergebene Map null ist, wird false zurueckgegeben.
        if (output == null || data == null) {
            return false;
        }
        //Es wird ein FileWriter erzeugund, um das uebergebene File zu beschreiben.
        try (FileWriter writer = (new FileWriter(output));) {

            //Es wird ein StringBuilder vorbereitet, welcher die ensprechende Syntax eines Formatierten Strings beinhaltet.
            StringBuilder formatStr = new StringBuilder("%-20s ");
            int maxSize = 0;
            //Die Keys der Map werden mit der ensprechenden Syntax, eines Formatierten Strings, dem StringBuilder hinzugefuegt.
            Object[] keys = data.keySet().toArray();
            for (int i = 1; i < data.keySet().size(); i++) {
                if (data.get(keys[i - 1]).size() > maxSize) {
                    maxSize = data.get(keys[i - 1]).size();
                }
                formatStr.append(" %-15s");
            }
            keys = null;
            formatStr.append("%n");
            //Der fertige und formatierte String wird mittels des FileWriters in ein File geschrieben.
            writer.write(String.format(formatStr.toString(), data.keySet().toArray()));
            ArrayList<String> buffer;
            ArrayList<Double> dataSet;
            int index = 0;
            //Die Daten der Map werden mit der ensprechenden Syntax, eines Formatierten Strings, dem StringBuilder hinzugefuegt.

            while (index < maxSize) {
                buffer = new ArrayList();
                for (String key : data.keySet()) {
                    dataSet = (ArrayList<Double>) data.get(key);
                    if (dataSet.size() <= index) {
                        buffer.add("null");
                    } else if (dataSet.get(index) == null) {
                        buffer.add("null");
                    } else {
                        buffer.add(String.valueOf(dataSet.get(index)));
                    }
                }
                //Der fertige und formatierte String wird mittels des FileWriters in ein File geschrieben.
                writer.write(String.format(formatStr.toString(), buffer.toArray()));
                index++;
            }
            writer.flush();

        } catch (Exception e) {
            Out.log("Cannot write TXT export to disc.");
            return false;
        }

        return true;
    }

}
