/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.importer;

import de.gt.api.export.Exporter;
import de.gt.api.importer.Importer;
import de.gt.api.input.data.DataUnit;
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
    Map<String, List<DataUnit>> data;
    File file;

    public CsvImportExportTest() {
        path = System.getProperty("user.home") + "\\CSVEXPORTTEST.csv";
        data = new HashMap<String, List<DataUnit>>();
        file = new File(path);
    }

    @Before
    public void setUp() {

        ArrayList<DataUnit> values = new ArrayList<DataUnit>();
        values.add(new DataUnit("dsf"));
        values.add(new DataUnit(234235.4235D));
        values.add(new DataUnit(23435L));
        data.put("time", values);
        values.clear();
        values.add(new DataUnit("sdfsgd"));
        values.add(new DataUnit(099995.4235D));
        values.add(new DataUnit(999L));
        data.put("temp", values);

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

        Map<String, List<Object>> importedData = importer.importData(file);
        for (String key : importedData.keySet()) {
            for (int i = 0; i < importedData.get(key).size(); i++) {
                if (importedData.get(key).get(i).equals("null")) {
                    importedData.get(key).remove(i);
                    i--;
                }
            }
        }

        Assert.assertEquals(importedData, data);
    }

    @After
    public void afterTest() {
        file.delete();
    }

}
