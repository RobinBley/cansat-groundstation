/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input.data;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Kevin
 */
public class Data {

    private HashMap<String, DataUnit> values;

    /**
     * Liefert alle möglichen Keys zurück an die DatenUnits gebunden sind.
     *
     * @return Set<String> Ein Array aus den vorhandenen Data Keys
     */
    public Set<String> getKeys() {
        return values.keySet();
    }

    /**
     * Liefert einen einzelnen Datenwert in einem Kapselobjekt zurück.
     *
     * @return DataUnit Generelles Kapselobjekt für einen Datenwert
     */
    public DataUnit getDataUnit(String dataUnitKey) {
        return values.get(dataUnitKey);
    }
}
