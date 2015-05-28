package de.gt.gui.action.satellites;

import de.gt.gui.action.data.DialogAction;
import de.gt.gui.dialog.control.SatelliteAddDialog;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "de.gt.gui.action.AddSatelliteAction"
)
@ActionRegistration(
        displayName = "#CTL_AddSatelliteAction"
)
@ActionReference(path = "Menu/File/Satellites", position = 1000)
@Messages("CTL_AddSatelliteAction=Add")
public final class AddSatelliteAction extends DialogAction {

    private final JDialog addSatelliteDialog;

    public AddSatelliteAction() {
        this.addSatelliteDialog = new SatelliteAddDialog(mainWindow, true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addSatelliteDialog.setVisible(true);
    }
}
