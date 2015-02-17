package de.gt.input.dataformat;

import de.gt.input.data.DataUnit;
import java.util.ArrayList;

/**
 *
 * @author Robin
 */
public interface DataFormat {

    /**
     * Prueft ob Daten vorhanden sind.
     *
     * @return Liefert true, wenn Datenvorhanden sind. Andernfalls wird false
     * zurueckgegeben.
     */
    public boolean hasData();

    /**
     * Parst Daten vom vorgesehenen Datentyp
     *
     * @return Liefert Eine ArrayList zurück, welche Data-Unit-Objekte enthaelt
     */
    public ArrayList<DataUnit> parseData();
}
