/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input.config.access;

import de.gt.input.data.DataType;

/**
 *
 * @author Kevin
 */
public class ValueConfig {
    private DataType type;
    private String identifier;

    public ValueConfig(DataType type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public DataType getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }
}
