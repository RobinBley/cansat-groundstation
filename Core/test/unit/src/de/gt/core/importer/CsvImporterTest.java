/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.importer;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author Robin
 */
public class CsvImporterTest {

    @Test
    public void testImportData() {
        CsvImporter importer = new CsvImporter();
        File file = new File(System.getProperty("user.home") + "\\CSVEXPORTTEST.csv");
        Map<String, List<Object>> data = importer.importData(file);

        for (String key : data.keySet()) {
            System.out.println(key);
            for (Object datum : data.get(key)) {
                System.out.println(datum);

            }
        }

    }

}
