package de.gt.core.config;

import java.util.Collection;

/**
 * Stellt Daten dar, welche aus der Config gelesen werden
 * @author Kevin
 */
public class Config implements de.gt.api.config.Config{
    private final String name;
    private final String identifier;
    private final String format;
    private final Collection<String> keys;

    /**
     * Konstructor
     * @param name - Satellitenname
     * @param identifier - Satellitenidentifier
     * @param format - Übertragungsformat
     * @param keys - Schlüssel
     */
    public Config(String name, String identifier, String format, Collection<String> keys) {
        this.name = name;
        this.identifier = identifier;
        this.format = format;
        this.keys = keys;
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
        return keys;
    }
}
