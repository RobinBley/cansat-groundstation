package de.gt.gui.action.visualization;

import de.gt.api.config.Config;
import de.gt.api.datapipeline.DataPipeline;
import de.gt.gui.window.VisualizationTopComponent;
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
        id = "de.gt.gui.action.OpenVisualizationWindowAction"
)
@ActionRegistration(
        displayName = "#CTL_OpenVisualizationWindowAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/Window", position = 3333),
    @ActionReference(path = "Shortcuts", name = "D-G")
})
@Messages("CTL_OpenVisualizationWindowAction=Vizualization")
public final class OpenVisualizationWindowAction implements ActionListener {

    private final DataPipeline pipeline;

    public OpenVisualizationWindowAction() {
        //Auf Pipeline zugreifen
        pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pipeline.isConfigLoaded()) {
            //Config aus Pipeline auslesen
            Config c = pipeline.getConfig();

            //Neues Fenster für Graphenvisualisierung erzeugen
            VisualizationTopComponent visualizationWindow = new VisualizationTopComponent(c);
            
            //Fenster öffnen
            visualizationWindow.open();
        } else {
            JOptionPane.showMessageDialog(null, "Das Visualisierungsfenster steht nur zur Verfügung, wenn eine Satelitenkonfiguration geladen wurde");
        }
    }
}
