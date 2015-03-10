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
 * Diese Klasse regelt das exportieren von Daten in eine Txt-Datei.
 * @author Robin
 */
public class TxtExporter implements Exporter {

    @Override
    public boolean exportData(Map<String, List> data, File output) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            for (int i = 0; i < data.get(data.keySet().toArray()[0]).size(); i++) {
                StringBuilder buffer = new StringBuilder("%-15s %15s %n");

                for (String key : data.keySet()) {
                    if (data.get(key).size() <= i) {
                        buffer.append(", ").append(data.get(key).get(i));
                    } else {
                        buffer.append(", NULL");
                    }
                }
                buffer.append(buffer.toString());
                buffer.append(System.getProperty("line.separator"));
            }

            writer.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
