/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.importer;

import de.gt.importer.CsvImporter;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Robin
 */
public class CsvImporterTest {

    @Test
    public void testImportData() {
        CsvImporter importer = new CsvImporter();
        File file = new File(System.getProperty("user.home") + "\\TG-CSV-Exporttest.csv");
        Map<String, List<Double>> data = importer.importData(file);
        Assert.assertNotSame(data, new HashMap<String, List<Double>>());

        for (String key : data.keySet()) {
            System.out.println(key);
            for (Object datum : data.get(key)) {
                System.out.println(datum);
            }
        }

    }

}
