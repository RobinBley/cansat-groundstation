package de.gt.core.pipeline;

import de.gt.api.config.Config;
import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.log.Out;
import de.gt.api.relay.Configurable;
import de.gt.api.relay.Receiver;
import de.gt.api.relay.Relay;
import de.gt.api.sources.DataSource;
import de.gt.core.input.logging.JSONLogger;
import de.gt.core.relay.DataProvider;
import de.gt.core.transmission.SocketServer;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JOptionPane;
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
public class DataPipeline implements de.gt.api.datapipeline.DataPipeline, Receiver {

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

    //Logt für die Applikation
    private JSONLogger logger;

    //Cached die Daten die über das Relay kommen
    private Map<String, List<Double>> dataCache;

    private SocketServer server = new SocketServer();

    public DataPipeline() {
        //Cache für Datenpipeline
        dataCache = new HashMap<>();

        File logFile = getLogFile();

        if (logFile != null) {
            try {
                logger = new JSONLogger(getLogFile());

                //Logger am Relay registrieren
                registerDataReceiver(logger);
            } catch (IOException ex) {
                Out.log("Fehler beim initialisieren des JSON Logs für den Datenempfang");
            }
        }
    }

    private File getLogFile() {
        //Timestamp für Log File erzeugen
        Timestamp currenTimestamp = new Timestamp((new Date()).getTime());

        //Logfile Objekt erzeugen
        File logFile = new File("log/" + currenTimestamp.toString() + ".log");

        return logFile;
    }

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

        //Cache für die aufnahme von Satellitendaten vorbereiten
        setupCache(c);
    }

    private void setupCache(Config c) {
        //Cache leeren
        dataCache.clear();

        //Alle Keys für ValueLists anlegen
        c.getKeys().stream().forEach(k -> dataCache.put(k, new ArrayList<Double>()));
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
        boolean streamWasRunning = isStreamRunning();

        if (streamWasRunning) {
            stopStream();
        }

        //Receiver an Relay hängen
        pipeRelay.addReceiver(receiver);

        //Receiver in Set speichern
        receivingComponents.add(receiver);

        if (streamWasRunning) {
            startStream();
        }
    }

    /**
     * Entfernt einen Receiver wieder vom Ende der Pipeline, sodass der Receiver
     * keine weiteren Daten über die Pipeline empfängt.
     *
     * @param receiver
     */
    @Override
    public void unregisterDataReceiver(Receiver receiver) {
        boolean streamWasRunning = isStreamRunning();

        if (streamWasRunning) {
            stopStream();
        }

        //Receiver aus Relay entfernen
        pipeRelay.removeReceiver(receiver);

        //Receiver aus Set entfernen
        receivingComponents.remove(receiver);

        if (streamWasRunning) {
            startStream();
        }
    }

    public void startServer() {
        registerDataReceiver(server);
        server.start();
    }

    public void stopServer() {
        unregisterDataReceiver(server);
        server.stop();
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

        pipeRelay.addReceiver(this);

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

    @Override
    public void importData(Map<String, List<Double>> importData) {
        boolean doImport = false;

        if (this.config != null) {
            Set<String> importedKeySet = importData.keySet();
            Set<String> configuredKeySet = new HashSet<String>(this.config.getKeys());
            
            doImport = importedKeySet.equals(configuredKeySet);
        }

        if (doImport) {
            //Import an alle Komponenten durchgeben
            this.receivingComponents.stream()
                    .filter(c -> c instanceof Configurable)
                    .map(c -> (Configurable) c)
                    .forEach(c -> c.imported(importData));

            dataCache = importData;
        } else {
            JOptionPane.showMessageDialog(null, "Please load the satellite configuration which was used to export this data");
        }
    }

    @Override
    public Map<String, List<Double>> exportData() {
        //Gecachete Daten zurückgeben
        return dataCache;
    }

    @Override
    public void receive(Map<String, Double> data) {
        //Daten in Data Cache lagern
        for (Entry<String, Double> datum : data.entrySet()) {
            dataCache.get(datum.getKey()).add(datum.getValue());
        }
    }
}
