package de.gt.input.gen;

/**
 * Generates debug data in some format
 * @author mhuisi
 */
public interface DebugGenerator {
    
    /**
     * Generates the next debug datum.
     * @return generated datum
     */
    String nextData();
    
    /**
     * Checks if the debug generator has any data generated.
     * @return has data
     */
    boolean hasData();
    
}
