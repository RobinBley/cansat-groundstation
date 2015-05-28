package de.gt.api.config;

/**
 * Parst Konfigurationsdateien
 *
 * @author Kevin
 */
public interface ConfigParser {

    public Config parse(String configStr) throws InvalidConfigException;
}
