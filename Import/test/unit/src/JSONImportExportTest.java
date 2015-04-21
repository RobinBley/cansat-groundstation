/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import de.gt.export.Exporter;
import de.gt.export.JSONExport;
import de.gt.importer.JSONImporter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Robin
 */
public class JSONImportExportTest {

    File file;
    Map<String, List<Object>> data;

    public JSONImportExportTest() {
        file = new File(System.getProperty("user.home") + "\\JSONEXPORTTEST.json");
        data = new HashMap<String, List<Object>>();
    }

    @Before
    public void setUp() {
        ArrayList<Object> values = new ArrayList<Object>();
        values.add("1");
        values.add("234");
        values.add(null);
        data.put("time", (List<Object>) values.clone());
        values.clear();
        values.add("346");
        values.add("346345");
        values.add("899922");
        values.add("2");
        values.add("34");
        data.put("temp", (List<Object>) values.clone());

    }

    @After
    public void tearDown() {
        file.delete();
    }

    /**
     * Test of importData method, of class JSONImporter.
     */
    @Test
    public void testImportData() {
        Exporter exporter = new JSONExport();
        JSONImporter importer = new JSONImporter();
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));
        Assert.assertEquals(importer.importData(file), data);
        Assert.assertThat(importer.importData(null), null);
    }

}
