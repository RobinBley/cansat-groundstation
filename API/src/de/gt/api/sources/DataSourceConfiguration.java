package de.gt.api.sources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kevin
 */
public class DataSourceConfiguration {

    private Map<Class, Object> configuration = new HashMap<>();
    private List<Class> parameterSignature = new ArrayList<>();

    public void setConfigurationItem(Class key, Object item) {
        //TODO: Bug finden, crasht mit Class java.lang.Class cannot be cast to java.lang.Comparable
        configuration.put(key, item);

        //In Reihenfolge einfügen
        parameterSignature.add(key);
    }

    public List<Class> getParamSignature() {
        //Parametersignatur zum Verfgleich mit dem entsprechendem Datenquellenkonstruktor zurückgeben
        return parameterSignature;
    }

    public Object[] getParams() {
        //Gespeicherte Werte ordnen
        List<Object> parameters = new ArrayList<>();

        //Parameter nach Signaturliste sortieren
        parameterSignature.stream().forEach(p -> parameters.add(configuration.get(p)));

        //Parameter für Klasseninstanziierung zurückgeben
        return parameters.toArray();
    }
}
