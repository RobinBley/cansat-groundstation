/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse regelt das exportieren von Daten in eine CSV Datei.
 *
 * @author Robin
 */
public class CsvExport implements Exporter {

    @Override
    public boolean exportData(Map<String, List<Object>> data, File output) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            for (String key : data.keySet()) {
                writer.write(key + ";");
                for (int i = 0; i < data.get(key).size(); i++) {

                    writer.write(String.valueOf(data.get(key).get(i)) + ";");
                }
                writer.write(System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
