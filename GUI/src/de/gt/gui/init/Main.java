package de.gt.gui.init;

import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

/**
 * Startup script
 *
 * @author Kevin
 */
public class Main extends ModuleInstall {

    @Override
    public void restored() {
        /*
         Beim ZIP-Package: Hauptordner der Applikation als CWD
         Beim Standard-Debug-Build GUI/ als CWD
         */
        setUpDir("config");
        setUpDir("log");
    }

    private void setUpDir(String dirName) {
        File configDir = new File(dirName);

        if (!configDir.exists()) {
            //Ordner für Config erstellen
            configDir.mkdir();
        }
    }
}
