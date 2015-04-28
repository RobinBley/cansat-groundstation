/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action;

import de.gt.gui.dialog.SatelliteChooseDialog;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Manage",
        id = "de.gt.gui.action.ManageSatellitesAction"
)
@ActionRegistration(
        displayName = "#CTL_ManageSatellitesAction"
)
@ActionReference(path = "Menu/File/Satellites", position = 1100)
@Messages("CTL_ManageSatellitesAction=Manage")
public final class ManageSatellitesAction extends DialogAction {

    private final JDialog chooseSatelliteDialog;

    public ManageSatellitesAction() {
        this.chooseSatelliteDialog = new SatelliteChooseDialog(mainWindow, true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        chooseSatelliteDialog.setVisible(true);
    }
}
