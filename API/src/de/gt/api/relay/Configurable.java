package de.gt.api.relay;

import de.gt.api.config.Config;

/**
 * Interface für Konfigurierbare
 * Component (Die Components die auf
 * Config-Veränderungen reagieren müssen).
 * 
 * @author Kevin
 */
public interface Configurable {
    void configurableChanged(Config newConfig);
}
