/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.sources;

import javax.swing.JDialog;

/**
 *
 * @author Kevin
 */
public abstract class DataSourceConfigurationDialog extends JDialog {

    public abstract DataSourceConfiguration getConfiguration();
}
