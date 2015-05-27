/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.export;

import java.awt.Image;
import java.io.File;

/**
 *
 * @author Kevin
 */
public interface ImageExporter {
    public boolean exportData(Image image, File output);
}
