package de.gt.importer;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
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

        File file = new File(System.getProperty("user.home") + "\\JSONEXPORTTEST.json");
        JSONImporter importer = new JSONImporter();
        Map<String, List<Object>> data = importer.importData(file);

        for (String key : data.keySet()) {
            System.out.println(key);
            for (Object datum : data.get(key)) {
                System.out.println(datum);

            }
        }
    }

}
