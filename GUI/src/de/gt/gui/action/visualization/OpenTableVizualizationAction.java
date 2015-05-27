/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action.visualization;

import de.gt.api.config.Config;
import de.gt.api.datapipeline.DataPipeline;
import de.gt.gui.window.TableDisplayTopComponent;
import de.gt.gui.window.VisualizationTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Window",
        id = "de.gt.gui.action.visualization.OpenTableVizualizationAction"
)
@ActionRegistration(
        displayName = "#CTL_OpenTableVizualizationAction"
)
@ActionReference(path = "Menu/Window", position = 3333)
@Messages("CTL_OpenTableVizualizationAction=Tabellenvisualisierung")
public final class OpenTableVizualizationAction implements ActionListener {

    private DataPipeline pipeline;

    public OpenTableVizualizationAction() {
        this.pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pipeline.isConfigLoaded()) {
            //Config aus Pipeline auslesen
            Config c = pipeline.getConfig();

            //Neues Fenster für Graphenvisualisierung erzeugen
            TableDisplayTopComponent tableComponent = new TableDisplayTopComponent(c);

            //Fenster öffnen
            tableComponent.open();
        } else {
            JOptionPane.showMessageDialog(null, "Das Tabellen-Visualisierungsfenster steht nur zur Verfügung, wenn eine Satelitenkonfiguration geladen wurde");
        }
    }
}
