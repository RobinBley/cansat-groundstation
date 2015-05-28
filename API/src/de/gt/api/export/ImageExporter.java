package de.gt.api.export;

import java.awt.Image;
import java.io.File;

/**
 * Exportiert Daten als Bild
 * @author Kevin
 */
public interface ImageExporter extends Exporter{
    
    /**
     * Exportiert das Bild
     * @param image
     * @param output - output datei
     * @return ob der export erfolgreich war
     */
    public boolean exportData(Image image, File output);
}
