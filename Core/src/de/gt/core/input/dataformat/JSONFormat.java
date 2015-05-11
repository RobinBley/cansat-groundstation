package de.gt.core.input.dataformat;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.relay.Relay;
import de.gt.core.config.Config;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
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
        JSONObject jsonData = new JSONObject(data);

        Collection<String> keys = config.getValueConfigs();
        Map<String, Double> units = keys.stream()
                .filter(jsonData::has)
                .collect(Collectors.toMap(k -> k, jsonData::getDouble));

        if (relay != null) {
            relay.relay(units);
        }
    }

    @Override
    public void linkRelay(Relay relay) {
        this.relay = relay;
    }

}
