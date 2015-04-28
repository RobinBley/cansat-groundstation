package de.gt.api.sources;

import de.gt.api.input.dataformat.DataFormat;
import java.io.Closeable;

/**
 * Interface for all sources of data,
 * for instance USB
 * @author mhuisi
 */
public interface DataSource extends Closeable {
    
    /**
     * Opens this source.
     */
    void open();
    
    /**
     * Gets the formatter that this source
     * relays the data to.
     * @return formatter
     */
    DataFormat getFormatter();
    
    /**
     * Sets the formatter that this source 
     * relays the data to.
     * @param f - formatter
     */
    void setFormatter(DataFormat f);
    
}
