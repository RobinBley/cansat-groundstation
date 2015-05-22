///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package de.gt.core.input;
//
//import de.gt.api.relay.Receiver;
//
//import java.util.Map;
//import org.json.JSONObject;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import de.gt.core.config.Config;
//import de.gt.core.data.ConsoleOutput;
//import de.gt.core.input.dataformat.JSONFormat;
//import de.gt.core.relay.DataProvider;
//import java.util.ArrayList;
//import org.netbeans.junit.NbTestCase;
//
///**
// *
// * @author Robin
// */
//public class InputJSONTest extends NbTestCase implements Receiver {
//
//    private Map<String, Double> output;
//
//    public InputJSONTest(String name) {
//        super(name);
//    }
//
//    @Before
//    public void setUp() {
//        ArrayList<String> valueConfigs = new ArrayList<>();
//        valueConfigs.add("temp");
//        valueConfigs.add("test");
//
//        Config config = new Config("config", "identifier", "json", valueConfigs);
//
//        DataProvider relay = new DataProvider();
//        //Dem Relay out als Receiver hinzuzufuegen.
//        ConsoleOutput out = new ConsoleOutput();
//        relay.addReceiver(out);
//        relay.addReceiver(this);
//
//        JSONFormat dataFormat = new JSONFormat();
//        dataFormat.linkRelay(relay);
//        
//        //Daten selber generieren um beim testen ueberpruefen zu koennen.
//        JSONObject jdata = new JSONObject();
//
//        jdata.put("temp", 1234522);
//        dataFormat.parseData(jdata.toString());
//        //Zeile fehlerhaft durch refactoring (MARK!!!)
////        DebugJSON.createWithDebugGenerator(dataFormat);
//    }
//
//    /**
//     * Test of parseData method, of class JSONParser.
//     */
//    @Test
//    public void testInput() {
//        Assert.assertEquals(Double.valueOf(1234522), output.get("temp"));
//    }
//
//    @Override
//    public void receive(Map<String, Double> datum) {
//        output = datum;
//
//    }
//
//}
