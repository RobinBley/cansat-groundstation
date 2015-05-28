package de.gt.api.config;

import de.gt.api.gps.GPSKey;
import java.util.Collection;

/**
 * Stellt eine Config dar
 * @author Kevin
 */
public interface Config {

    public String getName();

    public String getIdentifier();

    public String getFormat();

    public Collection<String> getKeys();

    public String getSource();
    
    @Override
    public String toString();
    
    public GPSKey getGpsKey();
}
