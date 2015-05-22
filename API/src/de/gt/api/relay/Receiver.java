package de.gt.api.relay;

import java.util.Map;

/**
 * Represents components that are able to 
 * receive data and process them.
 * @author mhuisi
 */
public interface Receiver {
    
    /**
     * Receives a datum (triggered
     * when a datum was fully processed
     * in the input pipeline)
     * @param datum 
     */
    void receive(Map<String, Double> datum);
    
}