package de.gt.gui.sources;

import de.gt.api.streamutils.MapCollector;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;
import java.util.PrimitiveIterator.OfDouble;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Generator für zufällige Debugdaten
 *
 * @author mhuisi
 */
public class DebugGenerator {

    private final Map<String, Double> values;

    /**
     *
     * @param values - Default werte für die debug generation im key:start_value
     * mapping
     */
    private DebugGenerator(Map<String, Double> values) {
        this.values = values;
    }

    /**
     * Erstellt einen Debuggenerator mit default Debugkeys und -werten.
     *
     * @return generator
     */
    public static DebugGenerator createWithKeys(Collection<String> keys) {
        Map<String, Double> values = keys.stream()
                .collect(Collectors.toMap(k -> k, k -> 0.0));
        return new DebugGenerator(values);
    }

    /**
     * Erstellt einen linearen Satz an Daten.
     *
     * @return set of data
     */
    public Map<String, Double> generate() {
        OfDouble doubles = new Random().doubles(0.1, 4.0).iterator();
        Map<String, Double> ret = values.entrySet().stream()
                .filter(e -> Math.random() < 0.6)
                .map(e -> {
                    String k = e.getKey();
                    double last = values.get(k);
                    values.put(k, last + doubles.nextDouble());
                    return new SimpleEntry<>(k, last);
                }).collect(MapCollector.create());

        return ret;
    }

}
