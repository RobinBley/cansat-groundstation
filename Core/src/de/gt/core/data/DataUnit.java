/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.core.data;

/**
 *
 * @author Kevin
 */
public class DataUnit {

    private Object objectValue = null;
    private Double doubleValue = null;
    private String stringValue = null;
    private Long longValue = null;

    private DataType type;
 
    public DataUnit(DataType t) {
        this.type = t;
    }
    
    public DataUnit(Double doubleValue) {
        this.objectValue = doubleValue;
        this.doubleValue = doubleValue;
        this.type = DataType.DOUBLE;
    }

    public DataUnit(String stringValue) {
        this.objectValue = stringValue;
        this.stringValue = stringValue;
        this.type = DataType.STRING;
    }

    public DataUnit(Long longValue) {
        this.objectValue = longValue;
        this.longValue = longValue;
        this.type = DataType.LONG;
    }
    
    public Object getObjectValue() {
        return objectValue;
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
