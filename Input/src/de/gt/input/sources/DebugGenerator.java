package de.gt.input.sources;

import de.gt.input.data.DataType;
import de.gt.input.data.DataUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Generator for random debug data
 * @author mhuisi
 */
public final class DebugGenerator {
    
    private static final Set<Entry<String, DataType>> keys = new HashSet<>();
    static {
        // Data transmitted in the European CanSat 2014
        // Competition by the Team Gamma satellite
        keys.add(new SimpleEntry<>("time", DataType.LONG));
        keys.add(new SimpleEntry<>("longitude", DataType.STRING));
        keys.add(new SimpleEntry<>("latitude", DataType.STRING));
        keys.add(new SimpleEntry<>("altitude", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("accel_x", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("accel_y", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("accel_z", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("pressure", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("temp", DataType.DOUBLE));
        keys.add(new SimpleEntry<>("humidity", DataType.DOUBLE));
    }
    
    /**
     * Generates a random set of data.
     * @return set of data
     */
    public static Set<Entry<String, DataUnit>> generate() {
        Random r = new Random();
        return keys.stream().map(e -> {
            String k = e.getKey();
            switch (e.getValue()) {
                case LONG:
                    return new SimpleEntry<>(k, new DataUnit(r.nextLong()));
                case DOUBLE:
                    return new SimpleEntry<>(k, new DataUnit(r.nextDouble()));
                case STRING:
                    // This is, in the current test cases, ALWAYS gps data
                    // TODO: Unhandled so far, need further information on
                    // GPS data
            }
            return null;
        }).collect(Collectors.toSet());
    }
    
}
