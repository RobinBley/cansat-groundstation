package de.gt.importer;

import de.gt.api.importer.Importer;
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
import org.openide.util.lookup.ServiceProvider;

/**
 * Imports data from csv format
 * @author Robin
 */
@ServiceProvider(service = Importer.class)
public class CsvImporter implements Importer {

    @Override
    public Map<String, List<Double>> importData(File input) {
        //Eine HashMap wird erzeugt, welche spaeter die Daten des uebergebenen Files enthaelt.
        Map<String, List<Double>> data = new HashMap<>();

        //Ein BufferedReader wird erzeugt um die uebergebene Datei zu lesen.
        try (BufferedReader reader = new BufferedReader(new FileReader(input));) {

            String line;
            line = reader.readLine();
            String[] keys = line.split(";");
            String[] dataSet;
            //Fuer jeden Key der Datei wird eine ArrayList in der Map erzeugt.
            for (String key : keys) {
                data.put(key, new ArrayList<>());
            }
            //Die Daten der Datei werden der jeweiligen ArrayList in der Map hinzugefuegt.
            while ((line = reader.readLine()) != null) {
                dataSet = line.split(";");
                for (int i = 0; i < dataSet.length; i++) {
                    if (!dataSet[i].toLowerCase().equals("null")) {
                        data.get(String.valueOf(keys[i])).add(Double.valueOf(dataSet[i]));
                    } else if(!dataSet[i].toLowerCase().equals("")){
                        data.get(String.valueOf(keys[i])).add(null);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Die Hashmap, welche die Daten der uebergebenen Datei enthaelt, wird zurueckgegeben.
        return data;
    }

}
