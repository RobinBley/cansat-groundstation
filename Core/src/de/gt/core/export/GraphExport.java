package de.gt.core.export;

import de.gt.api.export.DataExporter;
import de.gt.api.export.ImageExporter;
import de.gt.api.log.Out;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.openide.util.lookup.ServiceProvider;

/**
 * Exporter for graphs
 *
 * @author Robin
 */
@ServiceProvider(service = de.gt.api.export.Exporter.class)
public class GraphExport implements ImageExporter {

    /**
     * Diese Funktion sorgt fuer das Exportieren eines Images in eine Datei.
     *
     * @param image Ein Image, welches gespeichert werden soll (BufferedImage,
     * ect).
     * @param output Datei, in der das Image gespechert werden soll.
     * @param extension Dateityp, welche die Datei haben soll (png, jpeg, etc).
     * @return true, wenn das Speichern erfolgreich war. Ansonsten false,
     */
    public boolean exportData(Image image, File output, String extension) {
        //Wenn das uebergebene Image oder das uebergebene File null ist, wird false zurueckgegeben.
        if (output == null || image == null) {
            return false;
        }
        //Wenn die Datei nicht die entsorechende Dateiendung hat, wird "png" als Default-Dateiendung hinzugefuegt.
        if (extension == null) {
            extension = "png";
        }
        if (!output.getPath().endsWith(extension)) {
            output = new File(output.getPath() + "." + extension);
        }
        try {
            //Die Daten werden in die Datei gesschrieben.
            ImageIO.write((RenderedImage) image, extension, output);
        } catch (IOException ex) {
            Out.log("Cannot write image to disc.");
            return false;
        }

        return true;
    }

    @Override
    public boolean exportData(Image image, File output) {
        return exportData(image, output, "jpg");
    }

    @Override
    public String getExporterName() {
        return "Graph Export";
    }

    @Override
    public String getFileExt() {
        return "jpg";
    }
    
    public String toString(){
        return getExporterName();
    }
    
}
