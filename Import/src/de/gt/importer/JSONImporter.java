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
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONImporter implements Importer {

    @Override
    public Map<String, List<Object>> importData(File input) {
        //Es wird geprueft ob eine Datei uebergeben wurde.
        if (input == null) {
            return null;
        }
        //Eine HashMap wird erzeugt, welche spaeter die Daten des uebergebenen Files enthaelt.
        HashMap<String, List<Object>> data = new HashMap<String, List<Object>>();

        try {
            //Ein BufferedReader wird erzeugt um die uebergebene Datei zu lesen.
            BufferedReader reader = new BufferedReader(new FileReader(input));
            //Die Daten Der uebergebenen Datei werden als JSONObject gespeichert.
            JSONObject jsonData;
            JSONArray jArray;
            ArrayList dataArray;
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonData = new JSONObject(line);
                //Es wird durch die Keys des JSONObject iteriert
                for (String key : jsonData.keySet()) {
                    //Das jeweilige JSONArrays eines Keys wird zu einer ArrayList konvertiert.
                    jArray = jsonData.getJSONArray(key);
                    dataArray = new ArrayList();
                    for (int i = 0; i < jArray.length(); i++) {
                        dataArray.add(jArray.get(i));
                    }
                    //Der jeweilige Key und das jeweilige Array eines Datensatztes werden der HashMap hinzugefuegt.
                    data.put(key, dataArray);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONImporter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(JSONImporter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        //Die Hashmap, welche die Daten der uebergebenen Datei enthaelt, wird zurueckgegeben.
        return data;
    }

}
