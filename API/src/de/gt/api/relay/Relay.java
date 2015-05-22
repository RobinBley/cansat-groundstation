package de.gt.api.relay;

import de.gt.api.config.Config;
import java.util.Map;

/**
 * Relay das Daten an mehrere Komponenten weiterleiten
 * und fehlende Daten filtert
 * @author Kevin
 */
public interface Relay {

    /**
     * Leitet eine Nachricht an alle Receiver weiter.
     * @param datum 
     */
    public void relay(Map<String, Double> datum);

    public void addReceiver(Receiver receiver);

    public boolean removeReceiver(Receiver receiver);

    public void relayConfigChange(Config c);
}
