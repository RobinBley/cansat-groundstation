package de.gt.importer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
        HashMap<String, List> data = new HashMap<String, List>();
        data = (HashMap<String, List>) importer.importData(file);

        for (String key : data.keySet()) {
            System.out.println(key);
            for (Object datum : data.get(key)) {
                System.out.println(datum);

            }
        }
        Assert.assertThat(importer.importData(null), null);
    }

}
