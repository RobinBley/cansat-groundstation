/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import de.gt.input.data.DataUnit;
import de.gt.input.logging.JSONLogger;
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
        DataUnit unit = new DataUnit("444444");
        Map data = new HashMap<String, DataUnit>();
        data.put("temp", unit);
        unit = new DataUnit(4L);
        logger.receive(data);
        data.put("time", unit);
        logger.receive(data);
        
        
        
        
        
        
    }

}
