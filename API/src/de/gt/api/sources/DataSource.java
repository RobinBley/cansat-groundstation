package de.gt.api.sources;

import de.gt.api.input.dataformat.DataFormat;
import java.io.Closeable;

/**
 * Interface for all sources of data, for instance USB
 *
 * @author mhuisi
 */
public interface DataSource extends Closeable {
    
    /**
     * Opens this source.
     */
    void open();

    /**
     * Linkt einen Parser als Zwischenstück 
     * für die Weiterverarbeitung in die Pipeline
     *
     * @param f - formatter
     */
    void linkParser(DataFormat f);
}
