package de.gt.api.config;

import java.io.IOException;
import java.util.List;

/**
 * Stellt einen Konfigurationslader dar
 * @author Kevin
 */
public interface ConfigLoader {
    public String load(String fileName) throws IOException;
}
