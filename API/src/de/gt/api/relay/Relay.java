/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.relay;

import java.util.Map;

/**
 *
 * @author Kevin
 */
public interface Relay {

    public void relay(Map<String, Double> datum);

    public void addReceiver(Receiver receiver);

    public boolean removeReceiver(Receiver receiver);
}
