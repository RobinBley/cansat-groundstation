/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input.data;

import de.gt.relay.Receiver;
import java.util.Map;

/**
 *
 * @author Robin
 */
public class ConsoleOutput implements Receiver{

    @Override
    public void receive(Map<String, DataUnit> datum) {
        System.out.println("Daten Empfangen:");
        
        
        
        
        String outData = null;
        for (String key : datum.keySet()){
            switch (datum.get(key).getType()) {

                    case DOUBLE:
                        outData = String.valueOf(datum.get(key).getDoubleValue());
                        break;
                    case LONG:
                        outData = String.valueOf(datum.get(key).getLongValue());
                        break;
                    case STRING:
                        outData = datum.get(key).getStringValue();
                        break;
                    default:

                }
                    
                    
                    
                    
                    
            System.out.println(key + ": " + outData);
        }
    }
    
}
