/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action.server;

import de.gt.api.datapipeline.DataPipeline;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Server",
        id = "de.gt.gui.action.server.StopServerAction"
)
@ActionRegistration(
        iconBase = "de/gt/gui/action/server/rsz_record_disabled.png",
        displayName = "#CTL_StopServerAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/Server", position = 3333, separatorAfter = 3383),
    @ActionReference(path = "Toolbars/Server", position = 0)
})
@Messages("CTL_StopServerAction=Stop Server")
public final class StopServerAction implements ActionListener {

    DataPipeline pipeline;

    public StopServerAction() {
        pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.pipeline.stopServer();
    }
}
