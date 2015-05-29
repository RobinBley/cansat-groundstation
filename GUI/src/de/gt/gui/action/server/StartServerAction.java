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
        id = "de.gt.gui.action.server.StartServerAction"
)
@ActionRegistration(
        iconBase = "de/gt/gui/action/server/rsz_record_hot.png",
        displayName = "#CTL_StartServerAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/Server", position = 3333, separatorBefore = 3283),
    @ActionReference(path = "Toolbars/Server", position = 100)
})
@Messages("CTL_StartServerAction=Start Server")
public final class StartServerAction implements ActionListener {

    DataPipeline pipeline;

    public StartServerAction() {
        pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.pipeline.startServer();
    }
}
