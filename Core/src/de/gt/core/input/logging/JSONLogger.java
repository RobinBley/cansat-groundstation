/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.input.logging;

import static de.gt.api.input.data.DataType.DOUBLE;
import static de.gt.api.input.data.DataType.LONG;
import static de.gt.api.input.data.DataType.STRING;
import de.gt.api.input.data.DataUnit;
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

    private File output;

    public JSONLogger(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        output = file;
    }

    @Override
    public void receive(Map<String, DataUnit> datum) {

        //Es wird ein FileWriter erzeugund, um das uebergebene File zu beschreiben.
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(output, true));

            JSONObject jData = new JSONObject();
            for (String key : datum.keySet()) {
                switch (datum.get(key).getType()) {

                    case DOUBLE:
                        jData.put(key, datum.get(key).getDoubleValue());
                        break;
                    case LONG:
                        jData.put(key, datum.get(key).getLongValue());
                        break;
                    case STRING:
                        jData.put(key, datum.get(key).getStringValue());
                        break;
                    default:
                        jData.put(key, datum.get(key).getObjectValue());
                        break;

                }
            }
            writer.write(jData.toString() + System.getProperty("line.separator"));
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JSONLogger.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
