/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Diese Klasse regelt das exportieren von Daten in eine Txt-Datei.
 *
 * @author Robin
 */
public class TxtExporter implements Exporter {

    @Override
    public boolean exportData(Map<String, List<Object>> data, File output) {
        try {
            if (output.exists()) {
                output.createNewFile();
            }
            FileWriter writer = (new FileWriter(output));

            StringBuilder formatStr = new StringBuilder("%-20s ");

            for (int i = 1; i < data.keySet().size(); i++) {
                formatStr.append(" %-15s");
            }
            formatStr.append("%n");

            writer.write(String.format(formatStr.toString(), data.keySet().toArray()));

            ArrayList<String> buffer;
            int index = 0;
            //MUSS NOCH GEFIXT WERDEN 
            //AUF JEDEN NOCH DIE BEDINGUNG FUER DIE WHILE
            while (index < data.get("time").size()) {
                buffer = new ArrayList();
                for (String key : data.keySet()) {
                    buffer.add((String) data.get(key).get(index));
                }
                writer.write(String.format(formatStr.toString(), buffer.toArray()));
                index++;
            }

//            for (int i = 0; i < data.get(data.keySet().toArray()[0]).size(); i++) {
//                
//                for (String key : data.keySet()) {
//                    if (data.get(key).size() <= i) {
//                        formatStr.append(", ").append(data.get(key).get(i));
//                    } else {
//                        formatStr.append(", NULL");
//                    }
//                }
//                formatStr.append(formatStr.toString());
//                formatStr.append(System.getProperty("line.separator"));
//            }
            writer.flush();
            writer.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
