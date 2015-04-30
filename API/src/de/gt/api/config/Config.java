/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.config;

import java.util.Map;

/**
 *
 * @author Kevin
 */
public interface Config {
    public String getName();

    public String getIdentifier();

    public String getFormat();

    public Map<String, Double> getValueConfigs();
}
