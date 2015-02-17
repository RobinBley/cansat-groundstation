package de.gt.input.data;

/**
 * Key type for values
 * loaded from the config
 * @author mhuisi
 */
public final class DataKey {
    
    private final String key;
    private final DataType type;

    public DataKey(String key, DataType type) {
        this.key = key;
        this.type = type;
    }
    
    public String getKey() {
        return key;
    }

    public DataType getType() {
        return type;
    }
    
}
