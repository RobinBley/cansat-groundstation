/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.config;

import java.util.Map;

/**
 *
 * @author Kevin
 */
public class Config {
    private String name;
    private String identifier;
    private String format;
    private Map<String, ValueConfig> valueConfigs;

    public Config(String name, String identifier, String format, Map<String, ValueConfig> valueConfigs) {
        this.name = name;
        this.identifier = identifier;
        this.format = format;
        this.valueConfigs = valueConfigs;
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getFormat() {
        return format;
    }

    public Map<String, ValueConfig> getValueConfigs() {
        return valueConfigs;
    }
}
