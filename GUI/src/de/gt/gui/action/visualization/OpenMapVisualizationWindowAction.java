package de.gt.gui.action.visualization;

import de.gt.api.datapipeline.DataPipeline;
import de.gt.api.gps.GPSKey;
import de.gt.gui.window.EarthTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Window",
        id = "de.gt.gui.action.OpenMapVisualizationWindowAction"
)
@ActionRegistration(
        displayName = "#CTL_OpenMapVisualizationWindowAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/Window", position = 3334),
    @ActionReference(path = "Shortcuts", name = "D-M")
})
@Messages("CTL_OpenMapVisualizationWindowAction=Map Vizualization")
public final class OpenMapVisualizationWindowAction implements ActionListener {

    private final DataPipeline pipeline;

    public OpenMapVisualizationWindowAction() {
        this.pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.pipeline.isConfigLoaded()) {
            JOptionPane.showMessageDialog(null, "The map visualization is only available if a satellite configuration was loaded");
        } else {
            GPSKey gpsKey = this.pipeline.getConfig().getGpsKey();

            if(gpsKey != null){
                //Neues Fenster für Graphenvisualisierung erzeugen
                EarthTopComponent mapVisualizationWindow = new EarthTopComponent(gpsKey);

                //Visualisierungsfenster anzeigen
                mapVisualizationWindow.open();
            } else{
                JOptionPane.showMessageDialog(null, "Map visualization is not available to this satellite because the application doesn't know how to fetch satellites position");
            }
        }
    }
}
