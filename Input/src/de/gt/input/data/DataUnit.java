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

    private Double doubleValue;
    private String stringValue;
    private Long longValue;

    private DataType type;

    public abstract String getString();

    public abstract Long getLong();

    public abstract Double getDouble();

    public DataUnit(Double doubleValue) {
        this.doubleValue = doubleValue;
        this.type = DataType.DOUBLE;
    }

    public DataUnit(String stringValue) {
        this.stringValue = stringValue;
        this.type = DataType.STRING;
    }

    public DataUnit(Long longValue) {
        this.longValue = longValue;
        this.type = DataType.LONG;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public DataType getType() {
        return type;
    }
}
