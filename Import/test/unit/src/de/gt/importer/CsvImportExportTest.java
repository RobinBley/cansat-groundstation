/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.importer;

import de.gt.export.CsvExport;
import de.gt.export.Exporter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class CsvImportExportTest {

    private String path;
    Map<String, List<Object>> data;
    File file;

    public CsvImportExportTest() {
        path = System.getProperty("user.home") + "\\CSVEXPORTTEST.csv";
        data = new HashMap<String, List<Object>>();
        file = new File(path);
    }

    @Before
    public void setUp() {

        ArrayList<Object> values = new ArrayList<Object>();
        values.add("1");
        values.add("6");
        data.put("time", (List<Object>) values.clone());
        values.clear();
        values.add("346");
        values.add("346345");
        values.add("8299922");
        values.add("829");
        data.put("temp", (List<Object>) values.clone());
        values.clear();
        values.add("921");
        data.put("co2", (List<Object>) values.clone());

    }

    /**
     * Test of importData method, of class Importer.
     */
    @Test
    public void testImportData() {
        Exporter exporter = new CsvExport();
        exporter.exportData(data, file);
        Importer importer = new CsvImporter();
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));
        Assert.assertEquals(importer.importData(file), data);
        Assert.assertThat(importer.importData(null), null);
    }

    @After
    public void afterTest() {
        file.delete();
    }

}
