package de.gt.input.dataformat;

/**
 *
 * @author Robin
 */
public interface DataFormat {
    
    /**
     * Prueft ob Daten vorhanden sind.
     * 
     * @return Liefert true, wenn Datenvorhanden sind. Andernfalls wird false zurueckgegeben.
     */
    public boolean hasData();
    
    
    /**
     * Parst Daten vom vorgesehenen Datentyp
     * 
     * @return Liefert Eine Data-Objekt zurueck, welches die geparsten Daten enthaelt
     */
    public Data paseData();
    
    
    
    
    
    
}
