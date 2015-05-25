package de.gt.api.config;

import java.util.Collection;

/**
 * Represents a config
 *
 * @author Kevin
 */
public interface Config {

    public String getName();

    public String getIdentifier();

    public String getFormat();

    public Collection<String> getKeys();

    public String toString();
}
