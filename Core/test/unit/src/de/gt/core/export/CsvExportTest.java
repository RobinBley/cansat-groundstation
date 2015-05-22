package de.gt.core.export;

import de.gt.api.export.Exporter;
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
        Map<String, List<Double>> data = new HashMap<String, List<Double>>();
        ArrayList<Double> values = new ArrayList<Double>();
        values.add(19D);
        values.add(2D);
        values.add(3D);
        values.add(4D);
        values.add(5D);
        data.put("time", (List<Double>) values.clone());
        values.clear();
        values.add(346D);
        values.add(346345D);
        values.add(899922D);
        values.add(8299922D);
        values.add(829D);
        data.put("temp", (List<Double>) values.clone());
        values.clear();
        values.add(6D);
        values.add(5D);
        values.add(2D);
        values.add(92D);
        data.put("co2", (List<Double>) values.clone());
        File file = new File(System.getProperty("user.home") + "\\TG-CSV-Exporttest.csv");
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));

    }

}
