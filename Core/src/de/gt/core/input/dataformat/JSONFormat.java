package de.gt.core.input.dataformat;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.relay.Relay;
import de.gt.api.streamutils.MapCollector;
import de.gt.core.config.Config;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Robin
 */
public class JSONFormat implements DataFormat {

    private Relay relay;
    private Config config;

    @Override
    public void parseData(String data) {
        try {
            JSONObject jsonData = new JSONObject(data);
            Collection<String> keys = config.getValueConfigs();
            Map<String, Double> units = keys.stream()
                    .map(k -> {
                        if (jsonData.has(k)) {
                            return new SimpleEntry<String, Double>(k, null);
                        }
                        return new SimpleEntry<>(k, jsonData.getDouble(k));
                    }).collect(MapCollector.create());

            if (relay != null) {
                relay.relay(units);
            }
        } catch (JSONException e) {
            //TODO: Log invalid data
        }
        
    }

    @Override
    public void linkRelay(Relay relay) {
        this.relay = relay;
    }

}
