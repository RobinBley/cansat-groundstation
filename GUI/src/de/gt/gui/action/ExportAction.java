/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action;

import de.gt.api.export.DataExporter;
import de.gt.api.export.ImageExporter;
import de.gt.api.export.PositionExporter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "de.gt.gui.action.export.ExportAction"
)
@ActionRegistration(
        displayName = "#CTL_ExportAction"
)
@ActionReference(path = "Menu/File", position = 3333)
@Messages("CTL_ExportAction=Export")
public final class ExportAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Lookup lookup = Lookup.getDefault();
        
        Collection<? extends PositionExporter> positionExporters = lookup.lookupAll(PositionExporter.class);
        Collection<? extends ImageExporter> imageExporters = lookup.lookupAll(ImageExporter.class);
        Collection<? extends DataExporter> exporter = lookup.lookupAll(DataExporter.class);
    }
}
