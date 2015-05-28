package de.gt.gui.action.satellites;

import de.gt.gui.action.data.DialogAction;
import de.gt.gui.dialog.control.SatelliteAddDialog;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.swing.JOptionPane;
import org.json.JSONObject;
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

    private final SatelliteAddDialog addSatelliteDialog;

    public AddSatelliteAction() {
        this.addSatelliteDialog = new SatelliteAddDialog(mainWindow, true);

        this.addSatelliteDialog.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                JSONObject config = addSatelliteDialog.getConfig();

                if (config != null) {
                    //Callback methode aufrufen
                    configJSONCreated(config);
                }
            }
        });
    }

    public void configJSONCreated(JSONObject config) {
        //Dateiname für Config zusammensetzen
        String fileName = "config/" + (String) config.get("name") + ".json";

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))) {
            {
                //Config schreiben
                writer.write(config.toString());
            }
        } catch (IOException ex) {
            //Konnte Config nicht schreiben, message anzeigen
            JOptionPane.showMessageDialog(null, "Failed while writing satellite configuration");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e
    ) {
        addSatelliteDialog.setVisible(true);
    }
}
