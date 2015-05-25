package de.gt.gui.action.satellites;

import de.gt.api.config.Config;
import de.gt.api.datapipeline.DataPipeline;
import de.gt.api.relay.Configurable;
import de.gt.gui.action.DialogAction;
import de.gt.gui.dialog.SatelliteChooseDialog;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;
import java.util.stream.Collectors;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@ActionID(
        category = "Manage",
        id = "de.gt.gui.action.ManageSatellitesAction"
)
@ActionRegistration(
        displayName = "#CTL_ManageSatellitesAction"
)
@ActionReference(path = "Menu/File/Satellites", position = 1100)
@Messages("CTL_ManageSatellitesAction=Manage")
public final class ManageSatellitesAction extends DialogAction {

    private final SatelliteChooseDialog chooseSatelliteDialog;
    private final DataPipeline pipeline;

    public ManageSatellitesAction() {
        //Dialog der an Action gekoppelt wird realisieren
        this.chooseSatelliteDialog = new SatelliteChooseDialog(mainWindow, true);

        this.pipeline = Lookup.getDefault().lookup(DataPipeline.class);
        
        //Adapter für Dialog aufsetzen, der benachrichtigt, wenn der Dialog beendet wurde
        this.chooseSatelliteDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                //Config hat sich geändert, darum müssen alle Konfigurierbaren Komponenten benachrichtigt werden
                onConfigChanged(chooseSatelliteDialog.getConfig());
            }
        });
    }

    public void onConfigChanged(Config config) {
        this.pipeline.exchangeConfig(config);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Dialog zum auswählen eines Satelliten zeigen
        chooseSatelliteDialog.setVisible(true);
    }
}
