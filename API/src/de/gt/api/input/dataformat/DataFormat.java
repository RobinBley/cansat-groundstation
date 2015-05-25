package de.gt.api.input.dataformat;

import de.gt.api.relay.Relay;

/**
 * Stellt einen Datenparser in der
 * Input-Pipeline dar.
 * Fehlende Daten sollten mit key:null
 * ersetzt werden!
 * @author Robin
 */
public interface DataFormat {

    /**
     * Linkt das Relay als "Endstück der Pipeline" an den Parser (hier: Format)
     * 
     * @param relay 
     */
    public void linkRelay(Relay relay);
    
    /**
     * Parst Daten vom vorgesehenen Datentyp und gibt sie an ein
     * Relay weiter.
     * @param data - daten die geparst werden sollen
     */
    public void parseData(String data);
    
}
