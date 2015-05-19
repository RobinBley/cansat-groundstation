package de.gt.core.pipeline;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.relay.Receiver;
import de.gt.api.relay.Relay;
import de.gt.api.sources.DataSource;
import java.util.Set;

/**
 * @author Kevin
 *
 * Pipeline Service Provider wrapper um die Input Pipeline damit per Lookup
 * darauf zugegriffen werden kann
 *
 * DataSource | DataFormat | Relay -> Push an die Receiver
 *
 */
public class DataPipeline {

    private DataSource pipeSource;
    private DataFormat pipeParser;
    private Relay pipeRelay;

    private Set<Receiver> receivingComponents;

    /**
     * Tauscht den Parser aus und bindet den neuen Parser an die Pipeline an.
     *
     * @param newParser
     */
    public void exchangeParser(DataFormat newParser) {
        pipeParser = newParser;

        //Parser mit Relay linken
        pipeParser.linkRelay(pipeRelay);

        //Parser an Source koppeln
        pipeSource.linkParser(newParser);
    }

    /**
     * Tauscht das Relay aus und registriert die Komponenten beim neuen Relay.
     *
     * @param newRelay
     */
    public void exchangeRelay(Relay newRelay) {
        //Relay austauschen
        pipeRelay = newRelay;

        //Verbindung zwischen Parser und Relay wiederherstellen
        pipeParser.linkRelay(pipeRelay);

        //Komponenten beim neuen Relay registrieren
        receivingComponents.stream()
                .forEach(c -> pipeRelay.addReceiver(c));
    }

    /**
     * Tauscht die Datenquelle aus und linkt die neue Quelle an den Rest der
     * Pipeline
     *
     *
     * @param newSource
     */
    public void exchangeSource(DataSource newSource) {
        //Alte Quelle austauschen
        pipeSource = newSource;

        //Neue Quelle an den Parser koppeln
        pipeSource.linkParser(pipeParser);
    }

    /**
     * Registriert einen neuen Komponenten für den Datenempfang über die
     * Pipeline
     *
     * @param receiver
     */
    public void registerDataReceiver(Receiver receiver) {
        //Receiver in Set speichern
        receivingComponents.add(receiver);
    }

    /**
     * Entfernt einen Receiver wieder vom Ende der Pipeline, sodass der Receiver
     * keine weiteren Daten über die Pipeline empfängt.
     *
     * @param receiver
     */
    public void unregisterDataReceiver(Receiver receiver) {
        //Receiver aus Set entfernen
        receivingComponents.remove(receiver);
    }
}
