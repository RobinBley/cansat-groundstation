package de.gt.input.sources;

import java.io.Closeable;

/**
 * Interface for all sources of data,
 * for instance USB
 * @author mhuisi
 */
public interface DataSource extends Closeable {
    
    /**
     * Gets the next datum.
     * @return datum in some format
     */
    String nextData();
    
    /**
     * Checks if the DataSource has any data.
     * @return has any data
     */
    boolean hasData();
    
}
