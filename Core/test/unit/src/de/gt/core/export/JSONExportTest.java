package de.gt.core.export;

import de.gt.core.export.JSONExport;
import de.gt.core.export.Exporter;
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

    @Test
    public void testExportData() {
        Exporter exporter = new JSONExport();
        Map<String, List<Object>> data = new HashMap<String, List<Object>>();
        ArrayList<Object> values = new ArrayList<Object>();
        values.add("1");
        values.add("234");
        values.add(null);
        data.put("time", (List<Object>) values.clone());
        values.clear();
        values.add(2L);
        values.add(346345);
        values.add(899922.20032);
        values.add(222D);
        values.add("34");
        data.put("temp", (List<Object>) values.clone());
        File file = new File(System.getProperty("user.home") + "\\JSONEXPORTTEST.json");
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));
        

    }

}
