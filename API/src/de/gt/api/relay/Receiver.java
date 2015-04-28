package de.gt.api.relay;

import de.gt.core.data.DataUnit;
import java.util.Map;

/**
 * Represents components that are able to 
 * receive data and process them.
 * @author mhuisi
 */
public interface Receiver {
    
    void receive(Map<String, DataUnit> datum);
    
}
