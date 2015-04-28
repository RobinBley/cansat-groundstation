/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.export;

import de.gt.api.input.data.DataUnit;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *
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
    public boolean exportData(Map<String, List<DataUnit>> data, File output);

}
