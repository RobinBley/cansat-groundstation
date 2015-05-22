/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action.visualization;

import de.gt.api.relay.Relay;
import de.gt.gui.window.VisualizationTopComponent;
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

    private final Relay relay;
    
    public OpenVisualizationWindowAction(){
        this.relay = Lookup.getDefault().lookup(Relay.class);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Neues Fenster für Graphenvisualisierung erzeugen
        VisualizationTopComponent visualizationWindow = new VisualizationTopComponent(null);
        visualizationWindow.open();
    }
}
