/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.export;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Robin
 */
public class GraphExport {

    /**
     * Diese Funktion sorgt fuer das Exportieren eines Images in eine Datei.
     *
     * @param image Ein Image, welches gespeichert werden soll (BufferedImage, ect).
     * @param output Datei, in der das Image gespechert werden soll.
     * @param extension Dateityp, welche die Datei haben soll (png, jpeg, etc).
     * @return true, wenn das Speichern erfolgreich war. Ansonsten false,
     */
    public boolean exportData(Image image, File output, String extension) {
        if (output == null || image == null) {
            return false;
        }
        if(extension == null){
            extension = "png";
        }
        try {
            if (!output.getPath().endsWith(extension)) {
                output = new File(output.getPath() + "." + extension);
            }
            ImageIO.write((RenderedImage) image, extension, output);
        } catch (IOException ex) {
            Logger.getLogger(GraphExport.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

}
