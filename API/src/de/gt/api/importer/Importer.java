package de.gt.api.importer;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Stellt einen Import-Parser dar
 *
 * @author Robin
 */
public interface Importer {

    /**
     * Es wird eine Datei eingelesen und dessen Werte als Map zurueckgeliefert.
     *
     * @param input Datei, dessen Daten eingelesen werden sollen.
     * @return Eingelesene Daten in Form einer Map.
     */
    public Map<String, List<Double>> importData(File input);

    /**
     * Gibt an welche Dateiendungen durch den implementierenden Importer
     * importiert werden können
     *
     * @return String
     */
    public String importFileExt();
}
