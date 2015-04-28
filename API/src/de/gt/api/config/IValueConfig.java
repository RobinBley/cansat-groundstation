/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.config;

import de.gt.api.input.data.DataType;

/**
 *
 * @author Kevin
 */
public interface IValueConfig {

    public DataType getType();

    public String getIdentifier();
}
