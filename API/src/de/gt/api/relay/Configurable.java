package de.gt.api.relay;

import de.gt.api.config.Config;
import java.util.List;
import java.util.Map;

/**
 * Interface für Konfigurierbare Component (Die Components die auf
 * Config-Veränderungen reagieren müssen).
 *
 * @author Kevin
 */
public interface Configurable {

    /**
     * Getriggert wenn die Konfigurationsdatei
     * verändert wird
     * @param newConfig 
     */
    void configChanged(Config newConfig);
    
    public abstract void imported(List<Map<String, Double>> importData);
}
