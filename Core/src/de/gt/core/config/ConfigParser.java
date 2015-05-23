package de.gt.core.config;

import de.gt.api.config.InvalidConfigException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openide.util.lookup.ServiceProvider;

/**
 * Parses a config
 *
 * @author Kevin
 */
@ServiceProvider(service = de.gt.api.config.ConfigParser.class)
public class ConfigParser implements de.gt.api.config.ConfigParser {

    private static final String NAME_KEY = "name";
    private static final String IDENTIFIER_KEY = "identifier";
    private static final String FORMAT_KEY = "format";
    private static final String VALUES_KEY = "values";

    /**
     * Parses the config
     *
     * @param configStr - data to parse
     * @return parsed Config
     * @throws InvalidConfigException - config cannot be parsed
     */
    @Override
    public Config parse(String configStr) throws InvalidConfigException {
        JSONObject parsedConfig = new JSONObject(configStr);

        if (isValidConfig(parsedConfig)) {
            //Satellitendatenübertragungsformat bestimmen
            String format = parsedConfig.getString(FORMAT_KEY);

            if (!isSupportedFormat(format)) {
                //Exception, nicht unterstütztes Format
                throw new InvalidConfigException();
            }

            //Identifier des Satelliten feststellen
            String identifier = parsedConfig.getString(IDENTIFIER_KEY);

            //Einzelne Keys bestimmen
            JSONArray values = parsedConfig.getJSONArray(VALUES_KEY);

            //Name des Satelliten bestimmen
            String name = parsedConfig.getString(NAME_KEY);

            return new Config(name, identifier, format, jsonArrayToList(values));
        } else {
            throw new InvalidConfigException();
        }

    }

    private List<String> jsonArrayToList(JSONArray jsonArr) {
        //Länge des JSON Arrays bestimmen
        int len = jsonArr.length();

        //Speichert die Elemente als Liste
        List<String> list = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            //Element in Liste einfügen
            list.add(jsonArr.getString(i));
        }

        return list;
    }

    private boolean isValidConfig(JSONObject parsedConfig) {
        return !(parsedConfig.isNull(NAME_KEY)
                || parsedConfig.isNull(IDENTIFIER_KEY)
                || parsedConfig.isNull(FORMAT_KEY)
                || parsedConfig.isNull(VALUES_KEY));
    }

    private boolean isSupportedFormat(String format) {
        switch (format.toUpperCase()) {
            case "JSON":
                return true;
            default:
                return false;
        }
    }

}
