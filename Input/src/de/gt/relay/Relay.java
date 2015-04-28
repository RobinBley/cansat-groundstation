package de.gt.relay;

import de.gt.input.data.DataUnit;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Relay to move data from a formatter to several receivers.
 *
 * @author mhuisi
 */
public class Relay {

    private static final String GPS_DEFAULT = "$GPGGA,125000.0,6917.6938,N,1601.8514,E,1,3,0.95,34.5,M,17.8,M,,*65";

    private final Set<Receiver> receivers = new HashSet<>();
    private final Map<String, DataUnit> latest = new HashMap<>();

    private static Relay instance;

    public static Relay getInstance() {
        if (instance == null) {
            instance = new Relay();
        }

        return instance;
    }

    /**
     * Builds an entry that is either the default value if no values have been
     * received yet or the last received entry.
     *
     * @param key - key of the entry
     * @param def - default to build the entry with should there be no latest
     * values
     * @return entries
     */
    private SimpleEntry<String, DataUnit> buildLatestEntry(String key, DataUnit def) {
        DataUnit last = latest.getOrDefault(key, def);
        return new SimpleEntry<>(key, last);
    }

    /**
     * Registriert einen Receiver beim Relay damit dieser Daten erhalten kann.
     *
     * @param receiver Ein Komponent der das Receiver Interface implementiert
     */
    public void register(Receiver receiver) {
        receivers.add(receiver);
    }

    /**
     * Relays a datum to multiple receivers.
     *
     * @param datum
     */
    public void relay(Collection<Entry<String, DataUnit>> datum) {
        Map<String, DataUnit> cleaned = datum.stream()
                .map(e -> {
                    DataUnit u = e.getValue();
                    String k = e.getKey();
                    switch (u.getType()) {
                        case LONG:
                            if (u.getLongValue() == null) {
                                return buildLatestEntry(k, new DataUnit((long) 0));
                            }
                            break;
                        case DOUBLE:
                            if (u.getDoubleValue() == null) {
                                return buildLatestEntry(k, new DataUnit((double) 0));
                            }
                            break;
                        case STRING:
                            if (u.getStringValue() == null) {
                                return buildLatestEntry(k, new DataUnit(GPS_DEFAULT));
                            }
                            break;
                    }
                    latest.put(k, u);
                    return e;
                }).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        receivers.stream()
                .forEach(d -> d.receive(cleaned));
    }

}
