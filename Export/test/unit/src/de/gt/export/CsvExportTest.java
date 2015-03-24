/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.export;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Robin
 */
public class CsvExportTest {

    public CsvExportTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of exportData method, of class CsvExport.
     */
    @Test
    public void testExportData() {
        Exporter exporter = new CsvExport();
        Map<String, List<Object>> data = new HashMap<>();
        ArrayList<Object> values = new ArrayList<>();
        values.add("1");
        values.add("234");
        data.put("time", (List<Object>) values.clone());
        values.clear();
        values.add("346");
        values.add("346345");
        values.add("899922");
        data.put("temp", (List<Object>) values.clone());
        File file = new File(System.getProperty("user.home") + "\\CSVEXPORTTEST.csv");
        Assert.assertTrue(exporter.exportData(data, file));

    }

}
