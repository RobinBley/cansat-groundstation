package de.gt.input.sources;

import de.gt.input.gen.DebugGenerator;

/**
 * Debug stream source
 * @author mhuisi
 */
public class DebugSource implements DataSource {

    private DebugGenerator gen;
    
    public DebugSource(DebugGenerator gen) {
        this.gen = gen;
    }
    
    @Override
    public String nextData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
