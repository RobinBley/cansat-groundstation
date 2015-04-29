package de.gt.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONImporter {

    public Map<String, List<Double>> importData(File input) {
        //Es wird geprueft ob eine Datei uebergeben wurde.
        if (input == null) {
            return null;
        }
        //Eine HashMap wird erzeugt, welche spaeter die Daten des uebergebenen Files enthaelt.
        HashMap<String, List<Double>> data = new HashMap<String, List<Double>>();

        //Ein BufferedReader wird erzeugt um die uebergebene Datei zu lesen.
        try(BufferedReader reader = new BufferedReader(new FileReader(input));) {
            
            //Die Daten Der uebergebenen Datei werden als JSONObject gespeichert.
            JSONArray jArray;
            String line = null;
            JSONObject jsonData;

            while ((line = reader.readLine()) != null) {
                jsonData = new JSONObject(line);

                //Es wird versucht ein JSONArray aus der Zeile zuerzeugen.
                for (String key : jsonData.keySet()) {

                    try {
                        //Es wird druch das jeweilige JSONArray iteriert und die Werte in die Map geschrieben.
                        jArray = jsonData.getJSONArray(key);
                        for (int i = 0; i < jArray.length(); i++) {

                            data.get(key).add(jArray.getDouble(i));

                            //mit den einzelnen werten arbeiten!!
                        }
                        //Der Wert eines keys wird der Liste des Keys in der Map hinzugefuegt.
                    } catch (Exception exc) {
                        data.get(key).add(jsonData.getDouble(key));

                    }

                }
            }
            //Die Hashmap, welche die Daten der uebergebenen Datei enthaelt, wird zurueckgegeben.
            return data;

        } catch (IOException e) {
            Logger.getLogger(JSONImporter.class.getName()).log(Level.SEVERE, null, e);

        } catch (JSONException e) {
            Logger.getLogger(JSONImporter.class.getName()).log(Level.SEVERE, null, e);

        }
        return data;
    }
}
