package de.gt.api.sources;

import de.gt.api.input.dataformat.DataFormat;
import java.io.Closeable;
import javax.swing.JDialog;

/**
 * Interface für alle Datenquellen
 *
 * @author mhuisi
 */
public interface DataSource extends Closeable {

    /**
     * Öffnet diese Quelle
     */
    void open();

    /**
     * Linkt einen Parser als Zwischenstück für die Weiterverarbeitung in die
     * Pipeline
     *
     * @param f - formatter
     */
    void linkParser(DataFormat f);

    default DataSourceConfigurationDialog getConfigurationDialog() {
        //Null meint hier es ist keine Konfiguration der Datenquelle über einen Dialog erforderlich
        return null;
    }
}
