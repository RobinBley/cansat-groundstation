package de.gt.input.dataformat;

import static de.gt.data.DataType.DOUBLE;
import static de.gt.data.DataType.LONG;
import static de.gt.data.DataType.STRING;
import de.gt.data.DataUnit;
import de.gt.relay.Relay;
import de.gt.input.config.access.Config;
import de.gt.input.config.access.ValueConfig;
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
        Collection<Entry<String, DataUnit>> units = new ArrayList<>();
        JSONObject jData = new JSONObject(data);
        Map<String, ValueConfig> keys = config.getValueConfigs();
        for (Entry<String, ValueConfig> entry : keys.entrySet()) {
            String key = entry.getKey();
            if (jData.has(key)) {
                DataUnit datum = null;
                switch (entry.getValue().getType()) {

                    case DOUBLE:
                        datum = new DataUnit(jData.getDouble(key));
                        break;
                    case LONG:
                        datum = new DataUnit(jData.getLong(key));
                        break;
                    case STRING:
                        datum = new DataUnit(jData.getString(key));
                        break;
                    default:
                        datum = new DataUnit(entry.getValue().getType());
                        break;

                }
                units.add(new SimpleEntry<>(key, datum));
            }
        }
        relay.relay(units);
    }

}