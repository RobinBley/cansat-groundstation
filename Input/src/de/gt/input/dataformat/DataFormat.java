package de.gt.input.dataformat;

/**
 *
 * @author Robin
 */
public interface DataFormat {

    /**
     * Parst Daten vom vorgesehenen Datentyp und gibt sie an ein
     * Relay weiter.
     * @param data - daten die geparst werden sollen
     */
    public void parseData(String data);
    
}
