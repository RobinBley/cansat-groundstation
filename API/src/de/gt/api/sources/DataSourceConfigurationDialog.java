/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.sources;

import javax.swing.JDialog;
import org.openide.windows.WindowManager;

/**
 *
 * @author Kevin
 */
public abstract class DataSourceConfigurationDialog extends JDialog {

    public DataSourceConfigurationDialog() {
        super(WindowManager.getDefault().getMainWindow(), true);
    }

    public abstract DataSourceConfiguration getConfiguration();
}
