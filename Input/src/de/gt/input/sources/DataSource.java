package de.gt.input.sources;

/**
 * Interface for all sources of data,
 * for instance USB
 * @author mhuisi
 */
public interface DataSource {

    String nextData();
    boolean hasData();
    
}
