/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.export;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse regelt das exportieren von Daten in eine formatierte Txt-Datei.
 *
 * @author Robin
 */
public class TxtExporter implements Exporter {

    @Override
    public boolean exportData(Map<String, List<Object>> data, File output) {
        if (output == null || data == null) {
            return false;
        }
        try {
            FileWriter writer = (new FileWriter(output));

            StringBuilder formatStr = new StringBuilder("%-20s ");

            int maxSize = 0;
            Object[] keys = data.keySet().toArray();
            for (int i = 1; i < data.keySet().size(); i++) {
                if (data.get(keys[i - 1]).size() > maxSize) {
                    maxSize = data.get(keys[i - 1]).size();
                }
                formatStr.append(" %-15s");
            }
            keys = null;
            formatStr.append("%n");

            writer.write(String.format(formatStr.toString(), data.keySet().toArray()));
            ArrayList<String> buffer;
            int index = 0;
            while (index < maxSize) {
                buffer = new ArrayList();
                for (String key : data.keySet()) {
                    if (data.get(key).size() <= index) {
                        buffer.add("null");
                    } else if (data.get(key).get(index) == null) {
                        buffer.add("null");
                    } else {
                        buffer.add((String) data.get(key).get(index));
                    }
                }
                writer.write(String.format(formatStr.toString(), buffer.toArray()));
                index++;
            }
            writer.flush();
            writer.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
