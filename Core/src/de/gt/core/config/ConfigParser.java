package de.gt.core.config;

import de.gt.api.config.InvalidConfigException;
import de.gt.api.gps.GPSKey;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openide.util.lookup.ServiceProvider;

/**
 * Parset eine Config
 *
 * @author Kevin
 */
@ServiceProvider(service = de.gt.api.config.ConfigParser.class)
public class ConfigParser implements de.gt.api.config.ConfigParser {

    private static final String NAME_KEY = "name";
    private static final String IDENTIFIER_KEY = "identifier";
    private static final String FORMAT_KEY = "format";
    private static final String VALUES_KEY = "values";
    private static final String SOURCE_KEY = "source";
    private static final String GPS_KEY = "gps";

    //GPS Subkeys
    private static final String GPS_KEY_LAT = "latitude";
    private static final String GPS_KEY_LONG = "longitude";
    private static final String GPS_KEY_ALT = "altitude";

    /**
     * Parset die Config
     *
     * @param configStr - Daten, die geparst werden sollen
     * @return geparste Config
     * @throws InvalidConfigException - Config kann nicht geparst werden
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

            //Standardmäßig kein Identifier, da dieser zurzeit noch nicht genutzt wird
            String identifier = null;

            if (parsedConfig.has(IDENTIFIER_KEY)) {
                //Identifier des Satelliten feststellen
                identifier = parsedConfig.getString(IDENTIFIER_KEY);
            }

            //Einzelne Keys bestimmen
            List<String> keys = jsonArrayToList(parsedConfig.getJSONArray(VALUES_KEY));

            //Versuchen GPS Konfiguration abzurufen
            GPSKey gps = null;

            if (parsedConfig.has(GPS_KEY) && parsedConfig.get(GPS_KEY) != null) {
                //Gps Container aus der Json Datei holen
                JSONObject gpsKeyCollection = parsedConfig.getJSONObject(GPS_KEY);

                //Checken ob alle benötigten Subkeys da sind
                if (gpsKeyCollection.has(GPS_KEY_LAT) && gpsKeyCollection.has(GPS_KEY_LONG) && gpsKeyCollection.has(GPS_KEY_ALT)) {
                    //Key für Breite auslesen
                    String latKey = gpsKeyCollection.getString(GPS_KEY_LAT);

                    //Key für Länge auslesen
                    String longKey = gpsKeyCollection.getString(GPS_KEY_LONG);

                    //Key für Höhe auslesen
                    String altKey = gpsKeyCollection.getString(GPS_KEY_ALT);

                    if (keys.contains(latKey) && keys.contains(longKey) && keys.contains(altKey)) {
                        //GPS Key als Kombinationskey erstellen
                        gps = new GPSKey(latKey, longKey, altKey);
                    }
                }
            }

            //Name des Satelliten bestimmen
            String name = parsedConfig.getString(NAME_KEY);

            //Datenquelle bestimmen
            String source = parsedConfig.getString(SOURCE_KEY);

            return new Config(name, identifier, format, source, keys, gps);
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
