/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.export;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class GraphExportTest {
    
    public GraphExportTest() {
    }
    @Test
    public void testExportData() {
        GraphExport exporter = new GraphExport();
        BufferedImage data = new BufferedImage(222, 222, BufferedImage.TYPE_INT_ARGB);
        File file = new File(System.getProperty("user.home") + "\\GraphEXPORTTEST.png");
        Assert.assertTrue(exporter.exportData(data, file, "png"));
        Assert.assertFalse(exporter.exportData(data, file, null));
    }
}
