package de.gt.api.config;

import java.io.IOException;
import java.util.List;

/**
 * Represents a config loader
 * @author Kevin
 */
public interface ConfigLoader {
    public String load(String fileName) throws IOException;
}
