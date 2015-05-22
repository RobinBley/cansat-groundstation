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
 * Parst JSON-Daten
 * @author Robin
 */
@ServiceProvider(service = DataFormat.class)
public class JSONFormat implements DataFormat {

    private Relay relay;
    private Config config;

    @Override
    public void parseData(String data) {
        if (relay != null) {
            Out.log("Kann JSON nicht parsen weil kein Relay gesetzt ist.");
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
            Out.log("Erhaltene Daten sind kein valides JSON.");
        }

    }

    @Override
    public void linkRelay(Relay relay) {
        this.relay = relay;
    }

}
