package de.gt.importer;

import de.gt.api.importer.Importer;
import de.gt.api.log.Out;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openide.util.lookup.ServiceProvider;

/**
 * Importiert Daten aus JSON
 * @author mhuisi
 */
@ServiceProvider(service = Importer.class)
public class JSONImporter implements Importer {

    @Override
    public Map<String, List<Double>> importData(File input) {
        //Es wird geprueft ob eine Datei uebergeben wurde.
        if (input == null) {
            Out.log("Zu lesende Datei exestiert nicht");
            return null;
        }
        //Eine HashMap wird erzeugt, welche spaeter die Daten des uebergebenen Files enthaelt.
        HashMap<String, List<Double>> data = new HashMap<>();
        //Ein BufferedReader wird erzeugt um die uebergebene Datei zu lesen.
        try (BufferedReader reader = new BufferedReader(new FileReader(input));) {
            //Die Daten Der uebergebenen Datei werden als JSONObject gespeichert.
            JSONArray jArray;
            String line;
            JSONObject jsonData;
            while ((line = reader.readLine()) != null) {
                jsonData = new JSONObject(line);
                //Es wird versucht ein JSONArray aus der Zeile zuerzeugen.
                for (String key : jsonData.keySet()) {
                    if(!data.containsKey(key)){
                        data.put(key, new ArrayList<>());
                    }
                    try {
                        //Es wird druch das jeweilige JSONArray iteriert und die Werte in die Map geschrieben.
                        jArray = jsonData.getJSONArray(key);
                        for (int i = 0; i < jArray.length(); i++) {
                            try {
                                data.get(key).add(jArray.getDouble(i));
                            } catch (Exception e) {
                                Out.log("IOException: Fehler beim Lesen von JSONObjekten");
                                return null;
                            }
                            //mit den einzelnen werten arbeiten!!
                        }
                        //Der Wert eines keys wird der Liste des Keys in der Map hinzugefuegt.
                    } catch (Exception exc) {
                        try {
                            data.get(key).add(jsonData.getDouble(key));
                        } catch (Exception e) {
                            Out.log("IOException: Fehler beim Lesen von JSONObjekten");
                            return null;
                        }
                    }
                }
            }
        } catch (IOException | JSONException e) {
            Out.log("IOException: Fehler beim Lesen von JSONObjekten");
            return null;
        }
        //Die Hashmap, welche die Daten der uebergebenen Datei enthaelt, wird zurueckgegeben.
        return data;
    }
}
