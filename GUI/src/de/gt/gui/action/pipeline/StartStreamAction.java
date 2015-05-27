/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action.pipeline;

import de.gt.api.datapipeline.DataPipeline;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "de.gt.gui.action.importer.StartStreamAction"
)
@ActionRegistration(
        iconBase = "de/gt/gui/action/pipeline/play_hot.png",
        displayName = "#CTL_StartStreamAction"
)
@ActionReference(path = "Toolbars/Stream", position = 200)
@Messages("CTL_StartStreamAction=Stream Starten")
public final class StartStreamAction implements ActionListener {

    private final DataPipeline pipeline;

    public StartStreamAction() {
        this.pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (this.pipeline.isConfigLoaded()) {
            this.pipeline.startStream();

            if (!this.pipeline.isStreamRunning()) {
                JOptionPane.showMessageDialog(null, "Fehler beim starten des Streams");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Es muss eine Satellitenkonfiguration ausgewählt werden, bevor der Stream gestartet werden kann!");
        }
    }
}
