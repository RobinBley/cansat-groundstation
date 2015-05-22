package de.gt.core.config;

import java.util.Collection;

/**
 * Represents the data read from a config
 * @author Kevin
 */
public class Config implements de.gt.api.config.Config{
    private final String name;
    private final String identifier;
    private final String format;
    private final Collection<String> valueConfigs;

    /**
     * Constructor
     * @param name - name of the satellite
     * @param identifier - identifier of the satellite
     * @param format - transmission format
     * @param valueConfigs - keys
     */
    public Config(String name, String identifier, String format, Collection<String> valueConfigs) {
        this.name = name;
        this.identifier = identifier;
        this.format = format;
        this.valueConfigs = valueConfigs;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public Collection<String> getKeys() {
        return valueConfigs;
    }
}
