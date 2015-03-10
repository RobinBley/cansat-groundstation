/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private Exporter expoerter;
    private File output;

    public Datalogger(Exporter exporter, File output) {
        this.expoerter = exporter;
        this.output = this.output;
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
        expoerter.exportData(map, output);

    }

}
