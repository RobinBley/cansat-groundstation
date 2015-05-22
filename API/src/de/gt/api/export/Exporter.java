package de.gt.api.export;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Represents a data exporter
 * @author Robin
 */
public interface Exporter {

    /**
     * Daten werden exportiert.
     *
     * @param data Daten, welche exportiert werden sollen.
     * @param output Die Datei, in der die Daten exportiert werden
     * @return Gibt true zurueck, wenn das Exportieren der Daten erfolgt ist.
     */
    public boolean exportData(Map<String, List<Double>> data, File output);

}
