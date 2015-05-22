package de.gt.core.sources;

import de.gt.api.streamutils.MapCollector;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Generator for random debug data
 *
 * @author mhuisi
 */
public class DebugGenerator {

    private final Map<String, Double> values;

    /**
     *
     * @param values - default values for the debug generation in a
     * key:start_value mapping.
     */
    private DebugGenerator(Map<String, Double> values) {
        this.values = values;
    }

    /**
     * Creates a debug generator with default initialized debug keys and values.
     *
     * @return generator
     */
    public static DebugGenerator createWithKeys(Collection<String> keys) {
        Map<String, Double> values = keys.stream()
                .collect(Collectors.toMap(k -> k, k -> 0.0));
        return new DebugGenerator(values);
    }

    /**
     * Generates a linear set of data.
     *
     * @return set of data
     */
    public Map<String, Double> generate() {
        Random r = new Random();
        return values.entrySet().stream()
                .filter(e -> Math.random() > 0.98)
                .map(e -> {
                    String k = e.getKey();
                    double last = values.get(k);
                    values.put(k, last + r.doubles(0.1, 4.0).findFirst().getAsDouble());
                    return new SimpleEntry<>(k, last);
                }).collect(MapCollector.create());
    }

}
