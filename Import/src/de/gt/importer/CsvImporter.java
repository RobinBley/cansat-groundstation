package de.gt.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robin
 */
public class CsvImporter implements Importer {

    @Override
    public Map<String, List> importData(File input) {
        //Es wird ueberprueft ob eine Datei uebergeben wurde.
        if (input == null) {
            return null;
        }
        //Eine HashMap wird erzeugt, welche spaeter die Daten des uebergebenen Files enthaelt.
        HashMap data = new HashMap();

        try {
            //Ein BufferedReader wird erzeugt um die uebergebene Datei zu lesen.
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;
            ArrayList keys = new ArrayList<String>();
            try {
                //Die erste Zeile der uebergebenen Datei wird eingelesen.
                line = reader.readLine();
                //Der String, der ersten Zeile, wird bei dem Zeichen ";" gesplittet,
                //da sich vorrausichtlich in dieser Zeile die Keys, der Werte befinden.
                //Die einzelnen Strings werden nun in einer ArrayList zwischengespeichert.
                for (String key : line.split(";")) {
                    keys.add(key);
                }
                //Die Anzahl der Keys wird zwischengespeichert.
                int size = keys.size();
                //Fuer jeden Key wird jeweils eine ArrayList erzeugt.
                ArrayList<ArrayList<String>> dataArray = new ArrayList<ArrayList<String>>();
                for (int i = 0; i < size; i++) {
                    dataArray.add(new ArrayList<String>());
                }
                //Jede ArrayList bekommt die zu dem jeweiligen Key zugehoerigen Daten.
                String[] splittedLine;
                while ((line = reader.readLine()) != null) {
                    splittedLine = line.split(";");
                    for (int index = 0; index < size; index++) {
                        dataArray.get(index).add(splittedLine[index]);
                    }
                }
                //Alle Keys werden mit den jeweiligen zugehoerigen Arraylists der HashMap uebergeben.
                for (int index = 0; index < size; index++) {
                    data.put(keys.get(index), dataArray.get(index));
                }

            } catch (IOException ex) {
                Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        //Die Hashmap, welche die Daten der uebergebenen Datei enthaelt, wird zurueckgegeben.
        return data;
    }

}
