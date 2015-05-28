package de.gt.core.export;

import de.gt.api.export.DataExporter;
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
public class TxtExportTest {

    @Test
    public void testExportData() {
        DataExporter exporter = new TxtExport();
        Map<String, List<Double>> data = new HashMap<String, List<Double>>();
        ArrayList<Double> values = new ArrayList<Double>();
        values.add(11111D);
        values.add(234D);
        values.add(null);
        data.put("time", (List<Double>) values.clone());
        values.clear();
        values.add(346D);
        values.add(346345D);
        values.add(899922D);
        values.add(2D);
        values.add(34D);
        data.put("temp", (List<Double>) values.clone());
        File file = new File(System.getProperty("user.home") + "\\TG-TXT-Exporttest.txt");
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));

    }

}
