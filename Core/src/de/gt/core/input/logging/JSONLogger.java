/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.input.logging;

import de.gt.api.relay.Receiver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class JSONLogger implements Receiver {

    private final File output;

    public JSONLogger(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        output = file;
    }

    @Override
    public void receive(Map<String, Double> datum) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true));) {
            JSONObject jData = new JSONObject();
            datum.entrySet().forEach(e -> jData.put(e.getKey(), e.getValue()));
            writer.write(jData.toString() + System.getProperty("line.separator"));
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(JSONLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
