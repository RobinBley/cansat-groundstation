/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.export;

import de.gt.api.gps.GPSKey;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author mhuisi
 */
public class KMLExportTest {

    @Test
    public void testExportData() {
        Map<String, List<Double>> data = new HashMap<>();
        String latKey = "latitude";
        data.put(latKey, Arrays.asList(8.944144665369445,
                8.94634636573061,
                8.948475878370516,
                8.950429947867049,
                8.952411905083814,
                8.953347831850383,
                8.951483644146459,
                8.952404689759057,
                8.954085532286719,
                8.955696380701617,
                8.954820384587929,
                8.953621527513317,
                8.951960117321253,
                8.951221169982734,
                8.950158564022551,
                8.949667517118973,
                8.949613472076392,
                8.949793902813763));
        String longKey = "longitude";
        data.put(longKey, Arrays.asList(53.04000334849751,
                53.03925439260457,
                53.03862748326305,
                53.03799155669221,
                53.03734362073385,
                53.03605838202149,
                53.03531646465707,
                53.03458183651122,
                53.03391577260481,
                53.03349852126767,
                53.03288197191088,
                53.03283087061915,
                53.03310031545019,
                53.03342536875182,
                53.03413150757858,
                53.03480656164324,
                53.03564464985725,
                53.0363239723987));
        String altKey = "altitude";
        data.put(altKey, Arrays.asList(0.0, 10.0, 20.0, 30.0, 40.0, 50.0,
                60.0, 70.0, 80.0, 90.0, 100.0, 110.0, 120.0, 130.0, 140.0,
                150.0, 160.0, 170.0));
        data.put("temp", Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,
                9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0));
        data.put("co2", Arrays.asList(10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0,
                80.0, 90.0, 100.0, 110.0, 120.0, 130.0, 140.0, 150.0, 160.0,
                170.0, 180.0));
        KMLExport x = new KMLExport();
        File output = new File(String.format("%s/TG-KML-Exporttest.kml",
                System.getProperty("user.home")));
        Assert.assertTrue(x.exportData(data, new GPSKey(latKey, longKey, altKey), output));
    }

}
