package de.gt.gui.action.satellites;

import de.gt.api.config.Config;
import de.gt.api.datapipeline.DataPipeline;
import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.input.dataformat.UnknownFormatException;
import de.gt.gui.action.DialogAction;
import de.gt.gui.dialog.SatelliteChooseDialog;
import de.gt.gui.sources.DebugJSON;
import de.gt.gui.sources.Serial;
import de.gt.gui.sources.UnknownDataSourceException;
import de.gt.gui.sources.builder.DataSourceBuilder;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

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
        //Konfiguration in der Pipeline austauschen
        this.pipeline.exchangeConfig(config);

        try {
            //Parser austauschen
            this.pipeline.exchangeParser(this.buildParser(config.getFormat()));

            //Eine neue Datenquelle in der Pipeline installieren (Geht nicht direkt, da ein Auswahldialog erforderlich sein kann)
            this.installDataSource(config.getSource());
        } catch (UnknownDataSourceException | UnknownFormatException ex) {
            //TODO: Show User Error, unknown source exception
            //TODO: Show user Error, unknown parser
        }
    }

    private void installDataSource(String sourceName) throws UnknownDataSourceException {
        DataSourceBuilder builder = new DataSourceBuilder();

        Class dataSourceTemplate;

        switch (sourceName) {
            case "Serial":
                dataSourceTemplate = Serial.class;
                break;
            case "Debug":
                dataSourceTemplate = DebugJSON.class;
                break;
            default:
                throw new UnknownDataSourceException();
        }

        builder.buildSource(dataSourceTemplate);
    }

    private DataFormat buildParser(String parserName) throws UnknownFormatException {
        Collection<? extends DataFormat> availableParsers = Lookup.getDefault().lookupAll(DataFormat.class);

        //Alle Parser durchlaufen und prüfen ob ein passender Parser dabei ist
        for (DataFormat parser : availableParsers) {
            if (parser.getName().equals(parserName)) {
                return parser;
            }
        }

        //Kein Passender Parser gefunden
        throw new UnknownFormatException();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Dialog zum auswählen eines Satelliten zeigen
        chooseSatelliteDialog.setVisible(true);
    }
}
