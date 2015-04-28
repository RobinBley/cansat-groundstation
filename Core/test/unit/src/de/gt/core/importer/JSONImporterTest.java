package de.gt.core.importer;

import de.gt.api.input.data.DataUnit;
import de.gt.importer.JSONImporter;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author Robin
 */
public class JSONImporterTest {

    /**
     * Test of importData method, of class JSONImporter.
     */
    @Test
    public void testImportData() {

        File file = new File(System.getProperty("user.home") + "\\TG-JSON-Exporttest.json");
        JSONImporter importer = new JSONImporter();
        Map<String, List<DataUnit>> data = importer.importData(file);

        for (String key : data.keySet()) {
            for (int i = 0; i < data.get(key).size(); i++) {
                System.out.println(key + data.get(key).get(i).getType() + ":" +data.get(key).get(i).getObjectValue());

            }
        }
    }

}
