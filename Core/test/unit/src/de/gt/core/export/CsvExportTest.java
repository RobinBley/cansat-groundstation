package de.gt.core.export;

import de.gt.api.export.Exporter;
import de.gt.api.input.data.DataUnit;
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
        Map<String, List<DataUnit>> data = new HashMap<String, List<DataUnit>>();
        ArrayList<DataUnit> values = new ArrayList<DataUnit>();
        values.add(new DataUnit(2432L));
        values.add(new DataUnit(4647.235D));
        values.add(new DataUnit("3sdafsdg34"));
        data.put("time", (List<DataUnit>) values.clone());
        values.clear();
        values.add(new DataUnit(435346L));
        values.add(new DataUnit("dwsdfe4"));
        values.add(new DataUnit(43534.646));
        values.add(new DataUnit(423534L));
        data.put("temp", values);
        
        File file = new File(System.getProperty("user.home") + "\\TG-Csv_Exporttest.csv");
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));

    }

}
