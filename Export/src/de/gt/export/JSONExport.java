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

public class JSONExport implements Exporter {

    @Override
    public boolean exportData(Map<String, List> data, File output) {

        JSONObject jsonData = new JSONObject();
        JSONArray jarray = new JSONArray();

        for (String key : data.keySet()) {

            for (int i = 0; i < data.get(key).size(); i++) {
                jarray.put(i, data.get(key).get(i));
            }
            jsonData.append(key, jarray);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(output.getPath()));
            writer.write(jsonData.toString());
            
        } catch (IOException ex) {
            Logger.getLogger(JSONExport.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
