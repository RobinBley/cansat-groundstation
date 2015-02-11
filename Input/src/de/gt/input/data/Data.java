/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input.data;

/**
 *
 * @author Kevin
 */
public abstract class Data {
    
    /**
     * Liefert alle möglichen Keys zurück an die DatenUnits gebunden sind.
     * 
     * @return String[] Ein Array aus den vorhandenen Data Keys
     */
    public abstract String[] getKeys();
    
    /**
     * Liefert einen einzelnen Datenwert in einem Kapselobjekt zurück.
     * 
     * @return DataUnit Generelles Kapselobjekt für einen Datenwert
     */
    public abstract DataUnit getDataUnit();
}
