/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input.test;

import de.gt.data.DataType;
import de.gt.data.DataUnit;
import de.gt.input.config.access.Config;
import de.gt.input.config.access.ValueConfig;
import de.gt.input.data.ConsoleOutput;
import de.gt.input.dataformat.JSONParser;
import de.gt.input.sources.DebugJSON;
import de.gt.relay.Receiver;
import de.gt.relay.Relay;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.netbeans.junit.NbTestCase;

/**
 *
 * @author Robin
 */
public class InputJSONTest extends NbTestCase implements Receiver {

    private Map<String, DataUnit> output;

    public InputJSONTest(String name) {
        super(name);
    }

    @Before
    public void setUp() {
        HashMap valueConfigs = new HashMap<String, ValueConfig>();
        valueConfigs.put("temp", new ValueConfig(DataType.LONG, "temp"));
//        valueConfigs.put("test", new ValueConfig(DataType.LONG, "test"));

        Config config = new Config("config", "identifier", "json", valueConfigs);

        Relay relay = new Relay();
        //Dem Relay out als Receiver hinzuzufuegen.
        ConsoleOutput out = new ConsoleOutput();
        relay.addReceiver(out);
        relay.addReceiver(this);

        JSONParser dataFormat = new JSONParser(relay, config);

        //Daten selber generieren um beim testen ueberpruefen zu koennen.
        JSONObject jdata = new JSONObject();

        jdata.put("temp", 1234522);
        dataFormat.parseData(jdata.toString());
        DebugJSON.createWithDebugGenerator(dataFormat);
    }

    /**
     * Test of parseData method, of class JSONParser.
     */
    @Test
    public void testInput() {
        Assert.assertEquals(Long.valueOf(1234522), output.get("temp").getLongValue());
    }

    @Override
    public void receive(Map<String, DataUnit> datum) {
        output = datum;

    }

}
