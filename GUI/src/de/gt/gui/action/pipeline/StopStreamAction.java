/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action.pipeline;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

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
    public StopStreamAction() {
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // TODO use context
    }
}
