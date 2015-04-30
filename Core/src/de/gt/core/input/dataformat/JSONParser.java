package de.gt.core.input.dataformat;

import de.gt.api.input.dataformat.DataFormat;
import static de.gt.api.input.data.DataType.DOUBLE;
import static de.gt.api.input.data.DataType.LONG;
import static de.gt.api.input.data.DataType.STRING;
import de.gt.api.input.data.DataUnit;
import de.gt.core.relay.Relay;
import de.gt.core.config.Config;
import de.gt.core.config.ValueConfig;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

/**
 *
 * @author Robin
 */
public class JSONParser implements DataFormat {

    private final Relay relay;
    private final Config config;

    /**
     *
     * @param relay Relay zum Weiterleiten der Daten
     * @param config Konfigurationsdaten zur idetifizierung von Daten
     */
    public JSONParser(Relay relay, Config config) {
        this.relay = relay;
        this.config = config;
    }

    @Override
    public void parseData(String data) {
        Collection<Entry<String, Double>> units = new ArrayList<>();
        JSONObject jData = new JSONObject(data);
        Map<String, ValueConfig> keys = config.getValueConfigs();
        for (Entry<String, ValueConfig> entry : keys.entrySet()) {
            String key = entry.getKey();
            if (jData.has(key)) {
                Double datum = jData.getDouble(key);
                units.add(new SimpleEntry<>(key, datum));
            }
        }
        relay.relay((Map<String, Double>) units);
    }

}
