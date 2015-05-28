package de.gt.api.relay;

import java.util.List;
import java.util.Map;

/**
 * Stellt Komponenten dar, welche Enddaten empfangen
 * und verarbeiten können.
 * @author mhuisi
 */
public interface Receiver {
    
    /**
     * Erhaelt ein Datum (getriggert
     * wenn ein Datum komplett in der Input-Pipeline
     * verarbeitet hat)
     * @param datum 
     */
    void receive(Map<String, Double> datum);
}
