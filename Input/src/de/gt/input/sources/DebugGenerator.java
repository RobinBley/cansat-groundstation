package de.gt.input.sources;

import de.gt.input.data.DataType;
import de.gt.input.data.DataUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Generator for random debug data
 * @author mhuisi
 */
public class DebugGenerator {
    
    private static final Collection<Entry<String, DataType>> keys = new ArrayList<>();
    static {
        // Data transmitted in the European CanSat 2014
        // Competition by the Team Gamma satellite
        keys.add(new SimpleEntry<>("time", DataType.LONG));
        keys.add(new SimpleEntry<>("gps", DataType.STRING));
        keys.add(new SimpleEntry<>("accel_x", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("accel_y", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("accel_z", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("pressure", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("temp", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("humidity", DataType.DOUBLE));
    }
    
    private final Map<String, Object> values;
    
    /**
     * 
     * @param values - default values for the debug generation 
     * in a key:start_value mapping. Allowed start_values include 
     * Strings, Doubles and Longs.
     */
    public DebugGenerator(Map<String, Object> values) {
        this.values = values;
    }
    
    public static DebugGenerator createWithDebugKeys() {
        Map<String, Object> values = keys.stream().map(e -> {
            String k = e.getKey();
            switch (e.getValue()) {
                case LONG:
                    return new SimpleEntry<>(k, 0);
                case DOUBLE:
                    return new SimpleEntry<>(k, 0.0);
                case STRING:
                    // It is assumed that all strings are GPS data
                    GPGGA gps = new GPGGA(6917.6938, 1601.8514, 34.5);
                    return new SimpleEntry<>(k, gps.toString());
            }
            return null;
        }).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        return new DebugGenerator(values);
    }
    
    /**
     * Generates a linear set of data.
     * @return set of data
     */
    public Collection<Entry<String, DataUnit>> generate() {
        return keys.stream().map(e -> {
            String k = e.getKey();
            switch (e.getValue()) {
                case LONG:
                    long longValue = (long) values.get(k);
                    values.put(k, longValue + 1);
                    return new SimpleEntry<>(k, new DataUnit(longValue));
                case DOUBLE:
                    double doubleValue = (double) values.get(k);
                    values.put(k, doubleValue + 1.0);
                    return new SimpleEntry<>(k, new DataUnit(doubleValue));
                case STRING:
                    // It is assumed that all strings are GPS data
                    GPGGA gps = GPGGA.createFromString((String) values.get(k));
                    GPGGA nextGPS = new GPGGA(gps.getLatitude() + 0.2, 
                            gps.getLongitude() + 0.4,
                            gps.getAltitude() + 0.5);
                    values.put(k, nextGPS.toString());
                    return new SimpleEntry<>(k, new DataUnit(gps.toString()));
            }
            return null;
        }).collect(Collectors.toList());
    }
    
}
