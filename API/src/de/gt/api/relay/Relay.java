/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.relay;

import de.gt.api.input.data.DataUnit;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Kevin
 */
public interface Relay {

    public void relay(Collection<Map.Entry<String, DataUnit>> datum);

    public void addReceiver(Receiver receiver);

    public boolean removeReceiver(Receiver receiver);
}
