package de.gt.api.relay;

import java.util.Map;

/**
 * Relay that remotes data
 * to multiple components and filters
 * missing data
 * @author Kevin
 */
public interface Relay {

    /**
     * Relays a datum to all receivers
     * @param datum 
     */
    public void relay(Map<String, Double> datum);

    public void addReceiver(Receiver receiver);

    public boolean removeReceiver(Receiver receiver);
}
