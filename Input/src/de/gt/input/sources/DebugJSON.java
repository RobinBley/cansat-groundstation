package de.gt.input.sources;

import de.gt.input.data.DataUnit;
import org.json.JSONObject;

/**
 * Debug source generating JSON
 * @author mhuisi
 */
public class DebugJSON implements DataSource {

    @Override
    public String nextData() {
        JSONObject json = new JSONObject();
        DebugGenerator.generate().forEach(entry -> {
            String k = entry.getKey();
            DataUnit u = entry.getValue();
            switch (u.getType()) {
                case LONG:
                    json.put(k, u.getLongValue());
                    break;
                case DOUBLE:
                    json.put(k, u.getDoubleValue());
                    break;
                case STRING:
                    json.put(k, u.getStringValue());
                    break;
            }
        });
        return json.toString();
    }

    @Override
    public boolean hasData() {
        // Debug source always has data
        return true;
    }
    
}
