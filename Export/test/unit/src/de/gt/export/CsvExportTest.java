package de.gt.export;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Robin
 */
public class CsvExportTest {

    /**
     * Test of exportData method, of class CsvExport.
     */
    @Test
    public void testExportData() {
        Exporter exporter = new CsvExport();
        Map<String, List<Object>> data = new HashMap<String, List<Object>>();
        ArrayList<Object> values = new ArrayList<Object>();
        values.add("19");
        values.add("2");
        values.add("3");
        values.add("4");
        values.add("5");
        values.add("6");
        data.put("time", (List<Object>) values.clone());
        values.clear();
        values.add("346");
        values.add("346345");
        values.add("899922");
        values.add("8299922");
        values.add("829");
        data.put("temp", (List<Object>) values.clone());
        values.clear();
        values.add("6");
        values.add("5");
        values.add("2");
        values.add("92");
        data.put("co2", (List<Object>) values.clone());
        File file = new File(System.getProperty("user.home") + "\\CSVEXPORTTEST.csv");
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));

    }

}
