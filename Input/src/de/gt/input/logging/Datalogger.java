package de.gt.input.logging;

import de.gt.export.Exporter;
import de.gt.input.data.DataUnit;
import de.gt.relay.Receiver;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse sorgt fuer das speichern von empfangenen Daten.
 * @author Robin
 */
public class Datalogger implements Receiver {

    private Exporter exporter;
    private File output;

    public Datalogger(Exporter exporter, File output) {
        this.exporter = exporter;
        this.output = output;
    }

    /**
     * Es werden Daten Empfangen und einem Exporter uebergeben.
     * Dieser Exporter speichert die Daten in ein File.
     * @param datum Ein Datensatz, welcher gespeichert werden soll.
     */
    @Override
    public void receive(Map<String, DataUnit> datum) {
        HashMap map = new HashMap<>();

        datum.keySet().stream().forEach((key) -> {
            map.put(key, map.get(key));
        });
        exporter.exportData(map, output);
        
        
        

    }

}
