package de.gt.core.export;

import de.gt.api.export.Exporter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openide.util.lookup.ServiceProvider;

/**
 * Diese Klasse regelt das Exportieren von Daten ins JSON-Format.
 *
 * @author Robin
 */
@ServiceProvider(service=de.gt.api.export.Exporter.class)
public class JSONExport implements Exporter {

    @Override
    public boolean exportData(Map<String, List<Double>> data, File output) {
        //Wenn das uebergebene File oder die uebergebene Map null ist, wird false zurueckgegeben.
        if (output == null || data == null) {
            return false;
        }
        //Die Daten der einzelnen Keys der uebergebenen Map werden jeweils ein JSONArray hinzugefuegt,
        //welche wiederum alle zusammen einem JSONObject hinzugefuegt werden.
        JSONArray jarray;
        JSONObject jsonData = new JSONObject();
        ArrayList<Double> dataSet;
        for (String key : data.keySet()) {
            jarray = new JSONArray();
            dataSet = (ArrayList<Double>) data.get(key);
            for (int i = 0; i < dataSet.size(); i++) {
                jarray.put(i, dataSet.get(i));
            }
            jsonData.put(key, jarray);
        }
        //Die Daten des JSONObjects werden in eine Datei geschrieben.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.getPath()));) {
            jsonData.write(writer);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(JSONExport.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.getPath()))) {
            //Die Daten des JSONObjects werden in eine Datei geschrieben.
            jsonData.write(writer);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(JSONExport.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }
}
