package de.gt.core.export;

import de.gt.api.export.Exporter;
import static de.gt.api.input.data.DataType.DOUBLE;
import de.gt.api.input.data.DataUnit;
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
    public boolean exportData(Map<String, List<DataUnit>> data, File output) {
        //Wenn das uebergebene File oder die uebergebene Map null ist, wird false zurueckgegeben.
        if (output == null || data == null) {
            return false;
        }

        JSONArray jarray;
        JSONObject jsonData = new JSONObject();
        int index;
        try {
            for (String key : data.keySet()) {
                jarray = new JSONArray();
                index = 0;
                for (DataUnit unit : data.get(key)) {
                    switch (unit.getType()) {
                        case DOUBLE:
                            jarray.put(index, unit.getDoubleValue());
                            index++;
                            break;
                        case LONG:
                            jarray.put(index, unit.getLongValue());
                            index++;
                            break;
                        case STRING:
                            jarray.put(index, unit.getStringValue());
                            index++;
                            break;
                        default:
                            jarray.put(index, unit.getObjectValue());
                            index++;
                            break;
                    }

                }
                jsonData.put(key, jarray);
            }
        } catch (Exception e) {

//            //Die Daten der einzelnen Keys der uebergebenen Map werden jeweils ein JSONArray hinzugefuegt,
//            //welche wiederum alle zusammen einem JSONObject hinzugefuegt werden.
//            jsonData = new JSONObject();
//            for (String key : data.keySet()) {
//                jarray = new JSONArray();
//                for (int i = 0; i < data.get(key).size(); i++) {
//                    jarray.put(i, data.get(key).get(i).getObjectValue());
//                }
//                jsonData.put(key, jarray);
//            }
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
