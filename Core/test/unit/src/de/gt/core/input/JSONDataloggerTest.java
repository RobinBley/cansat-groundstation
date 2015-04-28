package de.gt.core.input;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import de.gt.api.input.data.DataUnit;
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

    public JSONDataloggerTest() {
    }

    /**
     * Test of receive method, of class Datalogger.
     */
    @Test
    public void testReceive() throws IOException {
//        JSONLogger logger = new JSONLogger();
        File file = new File(System.getProperty("user.home") + "\\JSONLoggingTest.json");
        JSONLogger logger = new JSONLogger(file);
        DataUnit unit = new DataUnit(123.87854);
        Map data = new HashMap<String, DataUnit>();
        data.put("temp", unit);
        logger.receive(data);
        
        
        
        
        
        
    }

}
