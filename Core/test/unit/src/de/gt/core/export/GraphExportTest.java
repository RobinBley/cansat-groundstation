package de.gt.core.export;

import de.gt.core.export.GraphExport;
import java.awt.image.BufferedImage;
import java.io.File;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Robin
 */
public class GraphExportTest {

    @Test
    public void testExportData() {
        GraphExport exporter = new GraphExport();
        BufferedImage data = new BufferedImage(222, 2222, BufferedImage.TYPE_INT_ARGB);
        File file = new File(System.getProperty("user.home") + "\\TG-GRAPH-Exporttest.png");
        Assert.assertTrue(exporter.exportData(data, file, "png"));
    }
}
