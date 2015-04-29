package de.gt.api.streamutils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Creates entry stream collectors
 * @author mhuisi
 */
public class MapCollector<K, V> {

    /**
     * Creates a new collector
     * which allows to collect 
     * a stream of Entries<K, V> into
     * a a Map<K, V>.
     * @param <K>
     * @param <V>
     * @return 
     */
    public static <K, V> Collector<Entry<K, V>, ?, Map<K, V>> create() {
	return Collectors.toMap(Entry::getKey, Entry::getValue);
    }
    
}
