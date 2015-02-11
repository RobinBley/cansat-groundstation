/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input.data;

/**
 *
 * @author Kevin
 */
public abstract class DataUnit {

    private DataType type;

    public abstract String getString();

    public abstract Long getLong();

    public abstract Double getDouble();
}
