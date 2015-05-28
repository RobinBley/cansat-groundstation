package de.gt.api.export;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Stellt einen Datenexporter dar
 * @author Robin
 */
public interface DataExporter extends Exporter{

    /**
     * Exportiert Daten.
     *
     * @param data Daten, welche exportiert werden sollen.
     * @param output Die Datei, in der die Daten exportiert werden
     * @return Gibt true zurueck, wenn das Exportieren der Daten erfolgt ist.
     */
    public boolean exportData(Map<String, List<Double>> data, File output);

}
