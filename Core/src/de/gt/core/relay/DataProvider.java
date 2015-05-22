package de.gt.core.relay;

import de.gt.api.config.Config;
import de.gt.api.relay.Configurable;
import de.gt.api.relay.Receiver;
import de.gt.api.relay.Relay;
import de.gt.api.streamutils.MapCollector;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.openide.util.lookup.ServiceProvider;

/**
 * Relay to move data from a formatter to several receivers.
 *
 * @author mhuisi
 */
@ServiceProvider(service = Relay.class)
public class DataProvider implements Relay {

    private final Set<Receiver> receivers = new HashSet<>();
    private final Map<String, Double> latest = new HashMap<>();

    /**
     * Relays a datum to multiple receivers.
     *
     * @param datum
     */
    @Override
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
                }).collect(MapCollector.create());
        receivers.stream().forEach(d -> d.receive(cleaned));
    }

    @Override
    public void addReceiver(Receiver receiver) {
        receivers.add(receiver);
    }

    @Override
    public boolean removeReceiver(Receiver receiver) {
        return receivers.remove(receiver);
    }

    @Override
    public void relayConfigChange(Config c) {
        receivers.stream()
                .filter(r -> r instanceof Configurable)
                .forEach(r -> ((Configurable) r).configChanged(c));
    }
}
