package de.gt.core.config;

import de.gt.api.gps.GPSKey;
import java.util.Collection;

/**
 * Stellt die Daten dar, die aus der Config gelesen werden
 *
 * @author Kevin
 */
public class Config implements de.gt.api.config.Config {

    private final String name;
    private final String identifier;
    private final String format;
    private final Collection<String> valueConfigs;
    private final String source;
    private final GPSKey gps;

    /**
     * Konstructor
     *
     * @param name - Name des Satelliten
     * @param identifier - identifier des Satelliten
     * @param format - Übertragungsformat
     * @param valueConfigs - keys
     */
    public Config(String name, String identifier, String format, String source, Collection<String> valueConfigs, GPSKey gps) {
        this.name = name;
        this.identifier = identifier;
        this.format = format;
        this.valueConfigs = valueConfigs;
        this.source = source;
        this.gps = gps;
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public GPSKey getGpsKey() {
        return gps;
    }
}
