package de.gt.core.input;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import de.gt.core.input.logging.JSONLogger;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author Robin
 */
public class JSONDataloggerTest {

    /**
     * Test of receive method, of class Datalogger.
     */
    @Test
    public void testReceive() throws IOException {
        File file = new File(System.getProperty("user.home") + "\\TG-JSON-Loggingtest.json");

        JSONLogger logger = new JSONLogger(file);
        Map data = new HashMap<String, Double>();
        data.put("temp", 35345.43645);
        logger.receive(data);

    }

}
