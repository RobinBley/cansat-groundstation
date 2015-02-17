package de.gt.input.dataformat;

import de.gt.input.config.access.Config;
import de.gt.input.config.access.ValueConfig;
import de.gt.input.data.DataUnit;
import de.gt.input.sources.DataSource;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;

public class JSONParser implements DataFormat {

    private final DataSource dataSource;
    private final Config config;
    private ArrayList<DataUnit> units;

    /**
     *
     * @param dataSource Quelle der zu bearbeitenden Daten
     * @param config Konfigurationsdaten zur idetifizierung von Daten
     */
    public JSONParser(DataSource dataSource, Config config) {
        this.dataSource = dataSource;
        this.config = config;
        units = null;
    }

    @Override
    public boolean hasData() {
        return dataSource.hasData();
    }

    @Override
    public ArrayList<DataUnit> parseData() {
        if (hasData()) {
            units = new ArrayList<>();
            JSONObject jData = new JSONObject();

            jData.getJSONObject(dataSource.nextData());
            Map<String, ValueConfig> keys = config.getValueConfigs();
            while (dataSource.hasData()) {

                for (String key : keys.keySet()) {
                    if (jData.has(key)) {
                        switch (keys.get(key).getType()) {

                            case DOUBLE:
                                units.add(new DataUnit(jData.getDouble(key)));

                            case LONG:
                                units.add(new DataUnit(jData.getLong(key)));

                            case STRING:
                                units.add(new DataUnit(jData.getString(key)));

                            default:
                                break;

                        }
                    }
                }
            }
        }
        return units;
    }

}
