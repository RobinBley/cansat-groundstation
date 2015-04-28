/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.importer;

import de.gt.data.DataUnit;
import de.gt.export.Exporter;
import de.gt.export.JSONExport;
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

    private String path;
    Map<String, List<Object>> data;
    File file;

    public JSONImportExportTest() {
        path = System.getProperty("user.home") + "\\CSVEXPORTTEST.csv";
        data = new HashMap<String, List<Object>>();
        file = new File(path);
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
        values.add("222");
        values.add("34");
        data.put("temp", (List<Object>) values.clone());

    }

    /**
     * Test of importData method, of class Importer.
     */
    @Test
    public void testImportData() {
        Exporter exporter = new JSONExport();
        exporter.exportData(data, file);
        JSONImporter importer = new JSONImporter();
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));

        Map<String, List<DataUnit>> importedData = importer.importData(file);

        Assert.assertEquals(importedData, data);

    }

    @After
    public void afterTest() {
        file.delete();
    }

}
