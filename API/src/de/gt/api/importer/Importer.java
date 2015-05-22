package de.gt.api.importer;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Represents an import parser
 * @author Robin
 */
public interface Importer {

    /**
     * Es wird eine Datei eingelesen und dessen Werte als Map zurueckgeliefert.
     *
     * @param input Dein, dessen Daten eingelesen werden sollen.
     * @return Eingelesene Daten in Form einer Map.
     */
    public Map<String, List<Double>> importData(File input);
}
