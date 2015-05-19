/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.config;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Kevin
 */
public class Config implements de.gt.api.config.Config{
    private String name;
    private String identifier;
    private String format;
    private Collection<String> valueConfigs;

    public Config(String name, String identifier, String format, Collection<String> valueConfigs) {
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

    @Override
    public Collection<String> getValueConfigs() {
        return valueConfigs;
    }
}
