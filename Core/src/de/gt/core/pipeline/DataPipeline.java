package de.gt.core.pipeline;

import de.gt.api.config.Config;
import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.relay.Receiver;
import de.gt.api.relay.Relay;
import de.gt.api.sources.DataSource;
import de.gt.core.relay.DataProvider;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openide.util.lookup.ServiceProvider;

/**
 * @author Kevin
 *
 * Pipeline Service Provider wrapper um die Input Pipeline damit per Lookup
 * darauf zugegriffen werden kann
 *
 * DataSource | DataFormat | Relay -> Push an die Receiver
 *
 */
@ServiceProvider(service = de.gt.api.datapipeline.DataPipeline.class)
public class DataPipeline implements de.gt.api.datapipeline.DataPipeline {

    private DataSource pipeSource;
    private DataFormat pipeParser;
    private Config config;
    private boolean streamRunning = false;
    private Relay pipeRelay = new DataProvider();

    private Set<Receiver> receivingComponents = new HashSet<>();

    /**
     * Tauscht den Parser aus und bindet den neuen Parser an die Pipeline an.
     *
     * @param newParser
     */
    @Override
    public void exchangeParser(DataFormat newParser) {
        pipeParser = newParser;

        //Parser mit Relay linken
        pipeParser.linkRelay(pipeRelay);

        if (pipeSource != null) {
            //Parser an Source koppeln
            pipeSource.linkParser(newParser);
        }
    }

    @Override
    public void exchangeConfig(Config c) {
        this.config = c;
        
        if(this.pipeParser != null){
            this.pipeParser.configure(c);
        }
    }

    /**
     * Tauscht das Relay aus und registriert die Komponenten beim neuen Relay.
     *
     * @param newRelay
     */
    @Override
    public void exchangeRelay(Relay newRelay) {
        //Relay austauschen
        pipeRelay = newRelay;

        if (pipeParser != null) {
            //Verbindung zwischen Parser und Relay wiederherstellen
            pipeParser.linkRelay(pipeRelay);
        }

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
    @Override
    public void exchangeSource(DataSource newSource) {
        //Stream status updaten
        streamRunning = false;

        //Alte Quelle austauschen
        pipeSource = newSource;

        if (pipeParser != null) {
            //Neue Quelle an den Parser koppeln
            pipeSource.linkParser(pipeParser);
        }
    }

    /**
     * Registriert einen neuen Komponenten für den Datenempfang über die
     * Pipeline
     *
     * @param receiver
     */
    @Override
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
    @Override
    public void unregisterDataReceiver(Receiver receiver) {
        //Receiver aus Set entfernen
        receivingComponents.remove(receiver);
    }

    @Override
    public boolean isStreamRunning() {
        return streamRunning;
    }

    @Override
    public boolean isConfigLoaded() {
        return this.config != null;
    }

    /**
     * Gibt die aktuelle Pipeline Konfiguration zurück
     */
    @Override
    public Config getConfig() {
        return this.config;
    }

    @Override
    public boolean startStream() {
        if (pipeSource == null) {
            return false;
        }

        pipeSource.open();
        streamRunning = true;
        return true;
    }

    @Override
    public boolean stopStream() {
        if (pipeSource == null) {
            return false;
        }

        try {
            pipeSource.close();
            streamRunning = false;
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
