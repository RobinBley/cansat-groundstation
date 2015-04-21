/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.importer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
        File file = new File(System.getProperty("user.home") + "\\CSVEXPORTTEST.csv");
        HashMap<String, List> data = new HashMap<String, List>();
        data = (HashMap<String, List>) importer.importData(file);
        
        
        for (String key : data.keySet()){
            System.out.println(key);
            for(Object datum : data.get(key)){
                System.out.println(datum);
                
            }
        }
        
        Assert.assertThat(importer.importData(null), null);
                
    }
    
}
