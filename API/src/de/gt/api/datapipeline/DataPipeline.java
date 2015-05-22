package de.gt.api.datapipeline;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.relay.Receiver;
import de.gt.api.relay.Relay;
import de.gt.api.sources.DataSource;

/**
 * Stellt den Pipeline-Wrapper dar, welche
 * die Interaktion mit der Input-Pipeline
 * erlaubt.
 * @author Kevin
 */
public interface DataPipeline {

    /**
     * Tauscht den Parser aus und bindet den neuen Parser an die Pipeline an.
     *
     * @param newParser
     */
    public void exchangeParser(DataFormat newParser);

    /**
     * Tauscht das Relay aus und registriert die Komponenten beim neuen Relay.
     *
     * @param newRelay
     */
    public void exchangeRelay(Relay newRelay);

    /**
     * Tauscht die Datenquelle aus und linkt die neue Quelle an den Rest der
     * Pipeline
     *
     *
     * @param newSource
     */
    public void exchangeSource(DataSource newSource);

    /**
     * Registriert einen neuen Komponenten für den Datenempfang über die
     * Pipeline
     *
     * @param receiver
     */
    public void registerDataReceiver(Receiver receiver);

    /**
     * Entfernt einen Receiver wieder vom Ende der Pipeline, sodass der Receiver
     * keine weiteren Daten über die Pipeline empfängt.
     *
     * @param receiver
     */
    public void unregisterDataReceiver(Receiver receiver);
}
