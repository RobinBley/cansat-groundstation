package de.gt.export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Diese Klasse regelt das exportieren von Daten ins JSON-Format.
 *
 * @author Robin
 */
public class JSONExport implements Exporter {

    @Override
    public boolean exportData(Map<String, List<Object>> data, File output) {
        //Wenn das uebergebene File oder die uebergebene Map null ist, wird false zurueckgegeben.
        if (output == null || data == null) {
            return false;
        }

        JSONObject jsonData = new JSONObject();
        JSONArray jarray;
        //Die Daten der einzelnen Keys der uebergebenen Map werden jeweils ein JSONArray hinzugefuegt,
        //welche wiederum alle zusammen einem JSONObject hinzugefuegt werden.
        for (String key : data.keySet()) {
            jarray = new JSONArray();
            for (int i = 0; i < data.get(key).size(); i++) {
                jarray.put(i, data.get(key).get(i));
            }
            jsonData.put(key, jarray);
        }

        try {
            //Die Daten des JSONObjects werden in eine Datei geschrieben.
            BufferedWriter writer = new BufferedWriter(new FileWriter(output.getPath()));
            jsonData.write(writer);
            writer.flush();
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(JSONExport.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
