package de.gt.gui.sources.builder;

import de.gt.api.datapipeline.DataPipeline;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import de.gt.api.sources.DataSource;
import de.gt.api.sources.DataSourceConfiguration;
import de.gt.api.sources.DataSourceConfigurationDialog;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import org.openide.util.Lookup;

/**
 *
 * @author Kevin
 */
public class DataSourceBuilder extends WindowAdapter {

    private DataSourceConfigurationDialog dialog;
    private Class dataSourceClass;
    
    private DataPipeline pipeline;
    
    public DataSourceBuilder(){
        pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    public <T extends DataSource> void buildSource(Class<T> dataSourceClass) {
        try {
            //Default Interface Methode für das bereitstellen eines Konfigurationsdialoges für die Datenquelle
            Method configurationMethod = dataSourceClass.getMethod("getConfigurationDialog", null);

            //Statische Methode für den Konfigurationsdialog aufrufen um an eine Instanz des Konfigurationsdialoges zu gelangen
            Object configurationDialog = configurationMethod.invoke(null, null);

            if (configurationDialog instanceof DataSourceConfigurationDialog) {
                //Eigene Klasse als Callback für DataSource setzen
                ((DataSourceConfigurationDialog) configurationDialog).addWindowListener(this);

                //Dialog merken um später Informationen auszulesen
                this.dialog = (DataSourceConfigurationDialog) configurationDialog;

                //Klasse der Datenquelle merken
                this.dataSourceClass = dataSourceClass;

                //Konfigurationsdialog anzeigen
                this.dialog.setVisible(true);
            } else {
                throw new NoSuchMethodException();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            try {
                //Datenquelle erzeugen
                DataSource source = dataSourceClass.getConstructor().newInstance();

                //Quelle in der Pipeline installieren
                installSource(source);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex2) {
                //Kein Öffentlicher Konstruktor ohne Argumente
            }
        }
    }

    private void installSource(DataSource source) {
        this.pipeline.exchangeSource(source);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //Konfiguration aus Dialog auslesen
        DataSourceConfiguration config = dialog.getConfiguration();

        //Parametersignatur der Konfiguration auslesen
        Set<Class> paramSignature = config.getParamSignature();

        //Konstruktor suchen der auf die Konfigurationssignatur passt
        Constructor c = findConstructor(dataSourceClass, paramSignature);

        //Referenz für die zu bauende Datenquelle
        DataSource source = null;

        if (c == null) {
            //TODO: Kein Konstruktor für die Datenquelle gefunden, Fehlerhafte Programmierung des DataSourceConfigurationDialog
        } else {
            try {
                source = (DataSource) c.newInstance(config.getParams());

                //Datenquelle installieren
                installSource(source);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                //TODO: Show error to user
            }
        }

        //Objekte aufräumen
        cleanUp();
    }

    private Constructor findConstructor(Class classToSearchFor, Set<Class> paramSignature) {
        Constructor[] constructors = classToSearchFor.getConstructors();

        for (Constructor c : constructors) {
            if (Arrays.equals(c.getParameterTypes(), paramSignature.toArray())) {
                return c;
            }
        }

        //Kein passender Konstruktor gefunden
        return null;
    }

    private void cleanUp() {
        dataSourceClass = null;
        dialog = null;
    }

}
