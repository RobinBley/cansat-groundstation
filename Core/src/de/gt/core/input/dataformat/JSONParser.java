package de.gt.core.input.dataformat;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.core.relay.DataProvider;
import de.gt.core.config.Config;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONObject;

/**
 *
 * @author Robin
 */
public class JSONParser implements DataFormat {

    private final DataProvider relay;
    private final Config config;

    /**
     *
     * @param relay Relay zum Weiterleiten der Daten
     * @param config Konfigurationsdaten zur idetifizierung von Daten
     */
    public JSONParser(DataProvider relay, Config config) {
        this.relay = relay;
        this.config = config;
    }

    @Override
    public void parseData(String data) {
        JSONObject jData = new JSONObject(data);
        // TODO: Value configs?
        Collection<String> keys = config.getValueConfigs();
        Map<String, Double> units = keys.stream()
                .filter(jData::has)
                .collect(Collectors.toMap(k -> k, jData::getDouble));
        relay.relay(units);

    }

}
