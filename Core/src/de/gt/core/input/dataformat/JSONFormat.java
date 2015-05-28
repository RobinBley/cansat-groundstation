package de.gt.core.input.dataformat;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.log.Out;
import de.gt.api.relay.Relay;
import de.gt.api.streamutils.MapCollector;
import de.gt.api.config.Config;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;
import org.openide.util.lookup.ServiceProvider;

/**
 * Parset JSON daten
 *
 * @author Robin
 */
@ServiceProvider(service = DataFormat.class)
public class JSONFormat implements DataFormat {

    private Relay relay;
    private Config config;

    @Override
    public void parseData(String data) {
        if (relay == null) {
            Out.log("Cannot parse JSON because relay is not set.");
            return;
        }
        try {
            JSONObject jsonData = new JSONObject(data);
            Collection<String> keys = config.getKeys();
            Map<String, Double> units = new HashMap<>();

            keys.stream().map(k -> {
                if (!jsonData.has(k)) {
                    return new SimpleEntry<String, Double>(k, null);
                }
                try {
                    return new SimpleEntry<>(k, jsonData.getDouble(k));
                } catch (JSONException e) {
                    return new SimpleEntry<String, Double>(k, null);
                }
            }).forEach(e -> units.put(e.getKey(), e.getValue()));

            relay.relay(units);
        } catch (JSONException e) {
            Out.log("Received data is not valid JSON.");
        }

    }

    @Override
    public void linkRelay(Relay relay) {
        this.relay = relay;
    }

    @Override
    public String getName() {
        return "JSON";
    }

    @Override
    public void configure(Config c) {
        config = c;
    }
}
