/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input.test;

import de.gt.export.CsvExport;
import de.gt.export.Exporter;
import de.gt.importer.CsvImporter;
import de.gt.importer.Importer;
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
import org.netbeans.junit.NbTestCase;

/**
 *
 * @author Robin
 */
public class ImportExportTest extends NbTestCase {

    private Exporter exporter;
    private Importer importer;

    public ImportExportTest(String name) {
        super(name);
        exporter = new CsvExport();
        importer = new CsvImporter();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //Write into file
        Map<String, List> data = new HashMap<>();
        ArrayList values = new ArrayList();
        values.add("1");
        data.put("time", values);
        File file = new File("testDatei.csv");
        exporter.exportData(data, file);

        
        //read File
        Map<String, List> readData = importer.importData(file);
        System.out.println(readData.get("time").get(0));
        Assert.assertEquals(readData.get("time").get(0), "1");
    }

    @After
    public void tearDown() {
    }

}
