/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.importer;

import de.gt.api.export.Exporter;
import de.gt.core.export.JSONExport;
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

    private String path;
    Map<String, List<Double>> data;
    File file;

    public JSONImportExportTest() {
        path = System.getProperty("user.home") + "\\CSVEXPORTTEST.csv";
        data = new HashMap<String, List<Double>>();
        file = new File(path);
    }

    @Before
    public void setUp() {

        ArrayList<Double> values = new ArrayList<Double>();
        values.add(1D);
        values.add(234D);
        values.add(null);
        data.put("time", (List<Double>) values.clone());
        values.clear();

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

        Map<String, List<Double>> importedData = importer.importData(file);

        Assert.assertEquals(importedData, data);

    }

    @After
    public void afterTest() {
        file.delete();
    }

}
