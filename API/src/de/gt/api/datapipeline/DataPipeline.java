package de.gt.api.datapipeline;

import de.gt.api.config.Config;
import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.relay.Receiver;
import de.gt.api.relay.Relay;
import de.gt.api.sources.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Stellt den Pipeline-Wrapper dar, welche die Interaktion mit der
 * Input-Pipeline erlaubt.
 *
 * @author Kevin
 */
public interface DataPipeline {

    /**
     * Tauscht eine Config aus
     *
     * @param c
     */
    public void exchangeConfig(Config c);

    /**
     * Gibt die aktuelle Pipeline Konfiguration zurück
     */
    public Config getConfig();

    /**
     * Gibt an ob der Stream gerade läuft oder nicht.
     *
     * @return boolean
     */
    public boolean isStreamRunning();

    /**
     * Gibt ein Boolean zurück, ob die Config geladen ist.
     *
     * @return boolean
     */
    public boolean isConfigLoaded();

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

    /**
     * Versucht den Stream zu starten, dies ist nur möglich wenn alle Teile der
     * Pipeline miteinander verbunden sind.
     *
     * @return False beim erfolgreichen Stoppen des Streams, ansonsten False
     */
    public boolean startStream();

    /**
     * Versucht den Stream zu stoppen
     *
     * @return True beim erfolgreichen Stoppen des Streams, ansonsten False
     */
    public boolean stopStream();
    
    /**
     * Wird für das importieren von Daten in Datenpipeline genutzt, dadurch
     * werden alle vorher gecacheten Daten gelöscht.
     * 
     * @param importData 
     */
    public void importData(Map<String, List<Double>> importData);

    /**
     * Exportiert die in der Pipeline gecacheten Daten.
     * 
     * @return 
     */
    public Map<String, List<Double>> exportData();
}
