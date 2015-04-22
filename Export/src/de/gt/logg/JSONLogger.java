/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.logg;

import de.gt.export.Exporter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class JSONLogger implements Exporter {
    
    @Override
    public boolean exportData(Map<String, List<Object>> data, File output) {
        //Wenn das uebergebene File oder die uebergebene Map null ist, wird false zurueckgegeben.
        if (output == null || data == null) {
            return false;
        }
        try {
            if (!output.exists()){
                output.createNewFile();
            }
            //Es wird ein FileWriter erzeugund, um das uebergebene File zu beschreiben.
            FileWriter writer = new FileWriter(output, true);
            
            JSONObject jData = new JSONObject();
            for (String key : data.keySet()) {
                jData.put(key, data.get(key));
            }
            writer.write(jData.toString());
            writer.flush();
            writer.close();
            
        } catch (Exception e) {
            Logger.getLogger(JSONLogger.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        
        return true;
    }
    
}
