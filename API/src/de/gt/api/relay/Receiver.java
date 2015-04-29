package de.gt.api.relay;

import java.util.Map;

/**
 * Represents components that are able to 
 * receive data and process them.
 * @author mhuisi
 */
public interface Receiver {
    
    void receive(Map<String, Double> datum);
    
}
