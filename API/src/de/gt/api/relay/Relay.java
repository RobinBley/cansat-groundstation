package de.gt.api.relay;

import de.gt.api.config.Config;
import java.util.Map;

/**
 * Relay, das Daten an mehrere Komponenten weiterleiten
 * und fehlende Daten filtert
 * @author Kevin
 */
public interface Relay {

    /**
     * Leitet eine Nachricht an alle Receiver weiter.
     * @param datum 
     */
    public void relay(Map<String, Double> datum);

    /**
     * Fügt einen Reciever hinzu
     * @param receiver 
     */
    public void addReceiver(Receiver receiver);

    /**
     * Entfernt einen Receiver
     * @param receiver
     * @return ob der Receiver entfernt werden konnte
     */
    public boolean removeReceiver(Receiver receiver);

    /**
     * Informiert alle Receiver, dass sich die Config verändert hat
     * @param c - config
     */
    public void relayConfigChange(Config c);
}
