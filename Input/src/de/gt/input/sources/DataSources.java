package de.gt.input.sources;

/**
 * Interface for all sources of data,
 * for instance USB
 * @author mhuisi
 */
public interface DataSources {

    String nextData();
    boolean hasData();
    
}
