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
public class TxtExportTest {

    @Test
    public void testExportData() {
        Exporter exporter = new TxtExport();
        Map<String, List<DataUnit>> data = new HashMap<String, List<DataUnit>>();
        ArrayList<DataUnit> values = new ArrayList<DataUnit>();
        values.add(new DataUnit("test"));
        values.add(new DataUnit(234235.4235D));
        values.add(new DataUnit(23435L));
        data.put("time", values);
        values.clear();
        values.add(new DataUnit("fbbxbfgd"));
        values.add(new DataUnit(33395.4235D));
        values.add(new DataUnit(9522222L));
        data.put("temp", values);
        File file = new File(System.getProperty("user.home") + "\\TG-Txt_Exporttest.txt");
        Assert.assertTrue(exporter.exportData(data, file));
        Assert.assertFalse(exporter.exportData(null, null));
        Assert.assertFalse(exporter.exportData(data, null));
        Assert.assertFalse(exporter.exportData(null, file));

    }

}
