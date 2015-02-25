package de.gt.input.dataformat;

import de.gt.input.sources.Relay;
import de.gt.input.config.access.Config;
import de.gt.input.config.access.ValueConfig;
import static de.gt.input.data.DataType.DOUBLE;
import static de.gt.input.data.DataType.LONG;
import static de.gt.input.data.DataType.STRING;
import de.gt.input.data.DataUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class JSONParser implements DataFormat {

    private final Relay relay;
    private final Config config;

    /**
     *
     * @param r Relay zum Weiterleiten der Daten
     * @param config Konfigurationsdaten zur idetifizierung von Daten
     */
    public JSONParser(Relay r, Config config) {
        this.relay = r;
        this.config = config;
    }

    @Override
    public void parseData(String data) {
        Map<String, DataUnit> units = new HashMap<>();
        JSONObject jData = new JSONObject(data);
        Map<String, ValueConfig> keys = config.getValueConfigs();
        for (Entry<String, ValueConfig> entry : keys.entrySet()) {
            String key = entry.getKey();
            if (jData.has(key)) {
                switch (entry.getValue().getType()) {

                    case DOUBLE:
                        units.put(key, new DataUnit(jData.getDouble(key)));
                        break;
                    case LONG:
                        units.put(key, new DataUnit(jData.getLong(key)));
                        break;
                    case STRING:
                        units.put(key, new DataUnit(jData.getString(key)));
                        break;

                }
            }
        }
        relay.relay(units);
    }

}
