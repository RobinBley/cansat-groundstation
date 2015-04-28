package de.gt.importer;

import de.gt.temp.DataUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONImporter {

    public Map<String, List<DataUnit>> importData(File input) {
        //Es wird geprueft ob eine Datei uebergeben wurde.
        if (input == null) {
            return null;
        }
        //Eine HashMap wird erzeugt, welche spaeter die Daten des uebergebenen Files enthaelt.
        HashMap<String, List<DataUnit>> data = new HashMap<String, List<DataUnit>>();

        try {
            //Ein BufferedReader wird erzeugt um die uebergebene Datei zu lesen.
            BufferedReader reader = new BufferedReader(new FileReader(input));
            //Die Daten Der uebergebenen Datei werden als JSONObject gespeichert.
            JSONArray jArray;
            String line = null;
            DataUnit unit;
            Object value;
            JSONObject jsonData;

            while ((line = reader.readLine()) != null) {
                jsonData = new JSONObject(line);

                //Es wird versucht ein JSONArray aus der Zeile zuerzeugen
                for (String key : jsonData.keySet()) {

                    try {
                        for (int i = 0; i < jsonData.getJSONArray(key).length(); i++) {

                            try {
                                Double d = jsonData.getDouble(key);
                                if (d.toString().endsWith(".0")) {
                                    unit = new DataUnit(jsonData.getLong(key));
                                } else {
                                    unit = new DataUnit(jsonData.getDouble(key));
                                }
                            } catch (Exception e) {
                                try {
                                    unit = new DataUnit(jsonData.getJSONArray(key).getLong(i));
                                } catch (Exception ex) {
                                    try {
                                        unit = new DataUnit(jsonData.getJSONArray(key).getString(i));
                                    } catch (Exception exception) {
                                        unit = new DataUnit(jsonData.get(key).toString());
                                    }
                                }

                            }
                            if (data.containsKey(key)) {
                                data.get(key).add(unit);
                            } else {
                                data.put(key, new ArrayList<DataUnit>());
                                data.get(key).add(unit);
                            }

                            //mit den einzelnen werten arbeiten!!
                        }
                    } catch (Exception exc) {
                        try {
                            Double d = jsonData.getDouble(key);
                            if (d.toString().endsWith(".0")) {
                                unit = new DataUnit(jsonData.getLong(key));
                            } else {
                                unit = new DataUnit(jsonData.getDouble(key));
                            }
                        } catch (Exception e) {
                            try {
                                unit = new DataUnit(jsonData.getLong(key));
                            } catch (Exception ex) {
                                try {
                                    unit = new DataUnit(jsonData.getString(key));
                                } catch (Exception exception) {
                                    unit = new DataUnit(jsonData.get(key).toString());
                                }
                            }
                        }

                        if (data.containsKey(key)) {
                            data.get(key).add(unit);
                        } else {
                            data.put(key, new ArrayList<DataUnit>());
                            data.get(key).add(unit);
                        }

                    }

                }
                //Die Hashmap, welche die Daten der uebergebenen Datei enthaelt, wird zurueckgegeben.
            }
            return data;

        } catch (IOException e) {
            Logger.getLogger(JSONImporter.class.getName()).log(Level.SEVERE, null, e);

        } catch (JSONException e) {
            Logger.getLogger(JSONImporter.class.getName()).log(Level.SEVERE, null, e);

        }
        return data;
    }
}
