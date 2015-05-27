package de.gt.core.pipeline;

import de.gt.api.config.Config;
import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.relay.Receiver;
import de.gt.api.relay.Relay;
import de.gt.api.sources.DataSource;
import de.gt.core.relay.DataProvider;
import java.io.IOException;
import java.time.chrono.ThaiBuddhistChronology;
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

    //Quellteil der Pipeline
    private DataSource pipeSource;

    //Parser teil der Pipeline
    private DataFormat pipeParser;

    //Konfiguration für die Pipelinekomponenten
    private Config config;

    //Relay ist standardmäßig da
    private Relay pipeRelay = new DataProvider();

    //Speichert alle Receiver
    private Set<Receiver> receivingComponents = new HashSet<>();

    //Speichert den Thread in dem der pull Stream läuft, damit ist die ganze Pipeline gethreadet
    private Thread streamThread;

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

        if (this.pipeParser != null) {
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
     * Der Stream muss danach erneut gestartet werden.
     *
     * @param newSource
     */
    @Override
    public void exchangeSource(DataSource newSource) {
        //Thread für den Stream stoppen, weil der sonst zerfliegt
        stopStream();

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
        
        //Receiver an Relay hängen
        pipeRelay.addReceiver(receiver);
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
        
        //Receiver aus Relay entfernen
        pipeRelay.removeReceiver(receiver);
    }

    @Override
    public boolean isStreamRunning() {
        return streamThread != null && streamThread.isAlive();
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

        //DataSource threaden
        streamThread = new Thread(pipeSource::open);
        streamThread.start();

        return isStreamRunning();
    }

    @Override
    public boolean stopStream() {
        if (pipeSource == null) {
            return false;
        }

        try {
            //Stream beenden
            pipeSource.close();

            //Thread Objekt entfernen
            streamThread = null;

            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
