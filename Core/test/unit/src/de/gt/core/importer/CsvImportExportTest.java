/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.importer;

import de.gt.api.export.DataExporter;
import de.gt.api.importer.Importer;
import de.gt.core.export.CsvExport;
import de.gt.importer.CsvImporter;
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
public class CsvImportExportTest {

    private String path;
    Map<String, List<Double>> data;
    File file;

    public CsvImportExportTest() {
        path = System.getProperty("user.home") + "\\CSVEXPORTTEST.csv";
        data = new HashMap<String, List<Double>>();
        file = new File(path);
    }

    @Before
    public void setUp() {

        ArrayList<Double> values = new ArrayList<Double>();
        values.add(1D);
        values.add(32.4324D);
        data.put("time", (List<Double>) values.clone());
        
        
    }

    /**
     * Test of importData method, of class Importer.
     */
    @Test
    public void testImportData() {
        DataExporter exporter = new CsvExport();
        exporter.exportData(data, file);
        Importer importer = new CsvImporter();
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
