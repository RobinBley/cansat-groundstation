package de.gt.core.input.dataformat;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.log.Out;
import de.gt.api.relay.Relay;
import de.gt.api.streamutils.MapCollector;
import de.gt.core.config.Config;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.openide.util.lookup.ServiceProvider;

/**
 * Parses JSON data
 *
 * @author Robin
 */
@ServiceProvider(service = DataFormat.class)
public class JSONFormat implements DataFormat {

    private Relay relay;
    private Config config;

    @Override
    public void parseData(String data) {
        if (relay != null) {
            Out.log("Cannot parse JSON because relay is not set.");
        }
        try {
            JSONObject jsonData = new JSONObject(data);
            Collection<String> keys = config.getKeys();
            Map<String, Double> units = keys.stream()
                    .map(k -> {
                        if (!jsonData.has(k)) {
                            return new SimpleEntry<String, Double>(k, null);
                        }
                        try {
                            return new SimpleEntry<>(k, jsonData.getDouble(k));
                        } catch (JSONException e) {
                            return new SimpleEntry<String, Double>(k, null);
                        }
                    }).collect(MapCollector.create());
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

}
