package de.gt.core.export;

import de.gt.api.export.DataExporter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openide.util.lookup.ServiceProvider;

/**
 * Diese Klasse regelt das Exportieren von Daten ins JSON-Format.
 *
 * @author Robin
 */
@ServiceProvider(service = de.gt.api.export.Exporter.class)
public class JSONExport implements DataExporter {

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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.getPath()))) {
            //Die Daten des JSONObjects werden in eine Datei geschrieben.
            jsonData.write(writer);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("IOException: Daten konnten nicht im JSONFormat exportiert werden.");
            return false;
        }

        return true;
    }

    @Override
    public String getExporterName() {
        return "JSON";
    }

    @Override
    public String getFileExt() {
        return "json";
    }
    
    public String toString(){
        return getExporterName();
    }
}
