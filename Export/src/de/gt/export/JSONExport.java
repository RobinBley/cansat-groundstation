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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Diese Klasse regelt das exportieren von Daten im JSON-Format.
 *
 * @author Robin
 */
public class JSONExport implements Exporter {

    @Override
    public boolean exportData(Map<String, List<Object>> data, File output) {
        if (output == null || data == null) {
            return false;
        }

        JSONObject jsonData = new JSONObject();
        JSONArray jarray;

        for (String key : data.keySet()) {
            jarray = new JSONArray();
            for (int i = 0; i < data.get(key).size(); i++) {
                jarray.put(i, data.get(key).get(i));
            }
            jsonData.put(key, jarray);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output.getPath()));
            jsonData.write(writer);
            writer.flush();
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(JSONExport.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exception");
            return false;
        }
        return true;
    }

}
