package de.gt.gui.action.pipeline;

import de.gt.api.datapipeline.DataPipeline;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import static javax.swing.JOptionPane.showMessageDialog;

@ActionID(
        category = "File",
        id = "de.gt.gui.action.pipeline.StopStreamAction"
)
@ActionRegistration(
        iconBase = "de/gt/gui/action/pipeline/stop_normal.png",
        displayName = "#CTL_StopStreamAction"
)
@ActionReference(path = "Toolbars/Stream", position = 300)
@Messages("CTL_StopStreamAction=Stream Stoppen")
public final class StopStreamAction implements ActionListener {

    private DataPipeline pipeline;

    public StopStreamAction() {
        pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (!this.pipeline.isStreamRunning()) {
            showMessageDialog(null, "Der Stream muss erst gestartet werden, damit er wieder gestoppt werden muss");
        } else {
            if (!this.pipeline.stopStream()) {
                showMessageDialog(null, "Fehler beim stoppen des Streams");
            }
        }
    }
}
