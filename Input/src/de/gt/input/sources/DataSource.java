package de.gt.input.sources;

/**
 * Interface for all sources of data,
 * for instance USB
 * @author mhuisi
 */
public interface DataSource {
    
    /**
     * Gets the next datum
     * @return datum in some format
     */
    String nextData();
    
    /**
     * Checks if the DataSource has any data.
     * @return has any data
     */
    boolean hasData();
    
}
