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
public class JSONExportTest {

    public JSONExportTest() {
    }

    @Test
    public void testExportData() {
        Exporter exporter = new JSONExport();
        Map<String, List<Object>> data = new HashMap<>();
        ArrayList<Object> values = new ArrayList<>();
        values.add("1");
        values.add("234");
        values.add(null);
        data.put("time", (List<Object>) values.clone());
        values.clear();
        values.add("346");
        values.add("346345");
        values.add("899922");
        values.add("2");
        values.add("34");
        data.put("temp", (List<Object>) values.clone());
        File file = new File(System.getProperty("user.home") + "\\JSONEXPORTTEST.json");
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));

    }

}
