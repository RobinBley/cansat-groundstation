package de.gt.core.input.logging;

import de.gt.api.relay.Receiver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import org.json.JSONObject;

/**
 * Logs data in the JSON format
 * @author mhuisi
 */
public class JSONLogger implements Receiver {

    private final File output;

    /**
     * Constructor
     * @param file - file to log to
     * @throws IOException thrown when file cannot be opened
     */
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
            System.out.println("Cannot log to JSON.");
        }
    }

}
