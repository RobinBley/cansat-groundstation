/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Robin
 */
public interface Importer {

    /**
     * Es wird eine Datei eingelesen und dessen Werte als Map zurueckgeliefert.
     *
     * @param input Dein, dessen Daten eingelesen werden sollen.
     * @return Eingelesene Daten in From einer Map.
     */
    public Map<String, List> importData(File input);
}
