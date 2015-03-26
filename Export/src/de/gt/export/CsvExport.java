/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    public boolean exportData(Map<String, List<Object>> data, File output) {
        if (output == null || data == null) {
            return false;
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));

            StringBuffer buffer = new StringBuffer();
            int maxSize = 0;
            for (String key : data.keySet()) {
                buffer.append(key + ";");
                if (data.get(key).size() > maxSize) {
                    maxSize = data.get(key).size();
                }
            }
            writer.write(buffer.toString() + System.getProperty("line.separator"));
            int index = 0;
            while (index < maxSize) {
                buffer = new StringBuffer();
                for (String key : data.keySet()) {
                    if (data.get(key).size() <= index) {
                        buffer.append("null");
                    } else if (data.get(key).get(index) == null) {
                        buffer.append("null");
                    } else {
                        buffer.append((String) data.get(key).get(index));
                    }
                    buffer.append(";");
                }
                writer.write(buffer.toString() + System.getProperty("line.separator"));
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
