package de.gt.input.dataformat;

import de.gt.input.config.access.Config;
import de.gt.input.config.access.ValueConfig;
import de.gt.input.data.DataType;
import de.gt.input.data.DataUnit;
import de.gt.input.sources.DataSource;
import java.util.Map;
import org.json.JSONObject;

public class JSONParser implements DataFormat {

    private final DataSource dataSource;
    private final Config config;

    public JSONParser(DataSource dataSource, Config config) {
        this.dataSource = dataSource;
        this.config = config;
    }

    @Override
    public boolean hasData() {
        return dataSource.hasData();
    }

    @Override
    public DataUnit parseData() {

        JSONObject jData = new JSONObject();

        jData.getJSONObject(dataSource.nextData());

        Map<String, ValueConfig> keys = config.getValueConfigs();

        for (String key : keys.keySet()) {
            if (jData.has(key)) {
                switch (keys.get(key).getType()) {

                    case DOUBLE:
                        return new DataUnit(jData.getDouble(key));

                    case LONG:
                        return new DataUnit(jData.getLong(key));

                    case STRING:
                        return new DataUnit(jData.getString(key));

                    default:
                        break;

                }
            }
        }
        return null;
    }

}
