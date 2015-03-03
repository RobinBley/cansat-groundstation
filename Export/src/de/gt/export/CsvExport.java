/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.export;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class CsvExport implements Exporter {

    /**
     * Liefert einen existierenden Pfad einer CSV-Datei zurueck.
     *
     * @param output Datei, aus der der Pfad gelsen wird
     * @return Ein Pfad einer CSV-Datei
     */
    private String getPath(File output) {
        String path;
        if (output == null) {

            StringBuilder buffer = new StringBuilder();
            buffer.append(System.getProperty("user.dir"));
            buffer.append(String.valueOf(new Timestamp(new java.util.Date().getTime())));
            buffer.append(".csv");
            path = buffer.toString();

        } else if (!output.getParent().endsWith(".csv")) {
            path = output.getPath() + ".csv";
        } else {
            path = output.getPath();
        }
        return path;
    }

    @Override
    public boolean exportData(Map<String, List> data, File output) {
        String path = getPath(output);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (String key : data.keySet()) {

                for (int i = 0; i < data.get(key).size(); i++) {

                    writer.write(String.valueOf(data.get(key).get(i)) + ";");
                }
                writer.write(System.getProperty("line.separator"));
                writer.close();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
