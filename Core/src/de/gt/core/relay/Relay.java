package de.gt.core.relay;

import de.gt.api.relay.Receiver;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Relay to move data from a formatter to several receivers.
 *
 * @author mhuisi
 */
public class Relay {

    private final Set<Receiver> receivers = new HashSet<>();
    private final Map<String, Double> latest = new HashMap<>();

    private static Relay instance;

    public static Relay getInstance() {
        if (instance == null) {
            instance = new Relay();
        }

        return instance;
    }

    /**
     * Relays a datum to multiple receivers.
     *
     * @param datum
     */
    public void relay(Map<String, Double> datum) {
        Map<String, Double> cleaned = datum.entrySet().stream()
                .map(e -> {
                    String k = e.getKey();
                    Double v = e.getValue();
                    if (v == null) {
                        Double last = latest.getOrDefault(k, 0.0);
                        return new SimpleEntry<>(k, last);
                    }
                    latest.put(k, v);
                    return e;
                }).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        receivers.stream().forEach(d -> d.receive(cleaned));
    }

    public void addReceiver(Receiver receiver) {
        receivers.add(receiver);
    }

    public boolean removeReceiver(Receiver receiver) {
        return receivers.remove(receiver);
    }
}
