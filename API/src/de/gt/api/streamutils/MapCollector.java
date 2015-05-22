package de.gt.api.streamutils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Erstellt Entry stream collectors
 * @author mhuisi
 */
public class MapCollector {

    /**
     * Erstellt einen neuen Collector
     * welcher es erlaubt, einen Stream aus
     * Entries<K, V> in eine Map<K, V> zu
     * collecten.
     * @param <K>
     * @param <V>
     * @return 
     */
    public static <K, V> Collector<Entry<K, V>, ?, Map<K, V>> create() {
	return Collectors.toMap(Entry::getKey, Entry::getValue);
    }
    
}
