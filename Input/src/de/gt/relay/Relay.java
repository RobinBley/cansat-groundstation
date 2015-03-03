package de.gt.relay;

import de.gt.input.data.DataUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Relay to move data from a formatter
 * to several receivers.
 * @author mhuisi
 */
public class Relay {

    private Set<Receiver> receivers = new HashSet<>();
    private Map<String, DataUnit> latest = new HashMap<>();
    
    /**
     * Relays a datum to multiple receivers.
     * @param datum 
     */
    public void relay(Collection<Entry<String, DataUnit>> datum) {

    }
    
}
