/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.api.sources;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author Kevin
 */
public class DataSourceConfiguration {

    Map<Class, Object> configuration = new ConcurrentSkipListMap<>();

    public void setConfigurationItem(Class key, Object item) {
        //TODO: Bug finden, crasht mit Class java.lang.Class cannot be cast to java.lang.Comparable
        configuration.put(key, item);
    }

    public Set<Class> getParamSignature() {
        //Parametersignatur zum Verfgleich mit dem entsprechendem Datenquellenkonstruktor zurückgeben
        return configuration.keySet();
    }

    public Object[] getParams() {
        //Parameter für Klasseninstanziierung zurückgeben
        return configuration.values().toArray();
    }
}
