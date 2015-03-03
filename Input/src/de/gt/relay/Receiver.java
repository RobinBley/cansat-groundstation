package de.gt.relay;

import de.gt.input.data.DataUnit;
import java.util.Map;

/**
 * Represents components that are able to 
 * receive data and process them.
 * @author mhuisi
 */
public interface Receiver {
    
    void receive(Map<String, DataUnit> datum);
    
}
