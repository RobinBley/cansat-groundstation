package de.gt.gui.action;

import de.gt.api.relay.Relay;
import de.gt.gui.window.EarthTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private final Relay relay;

    public OpenMapVisualizationWindowAction() {
        this.relay = Lookup.getDefault().lookup(Relay.class);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Neues Fenster für Graphenvisualisierung erzeugen
        EarthTopComponent mapVisualizationWindow = new EarthTopComponent(null);

        //Datenkomponent an Relay anschließen
        relay.addReceiver(mapVisualizationWindow);

        //Visualisierungsfenster anzeigen
        mapVisualizationWindow.open();
    }
}
