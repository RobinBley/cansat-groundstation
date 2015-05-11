package de.gt.core.pipeline;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.relay.Receiver;
import de.gt.api.relay.Relay;
import de.gt.api.sources.DataSource;
import de.gt.core.reflection.ClassFinder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kevin
 *
 * Pipeline Service Provider wrapper um die Input Pipeline damit per Lookup
 * darauf zugegriffen werden kann
 *
 * DataSource | DataFormat | Relay -> Push an die Receiver
 *
 */
public class DataPipelineManager {

    public static final String SOURCES_PACKAGE_NAME = "de.gt.core.sources";
    public static final String PARSER_PACKAGE_NAME = "de.gt.core.input.dataformat";

    private DataSource actualSource;

    public void setDataFormat(String dataFormatName) throws ClassNotFoundException{
        Relay distributor = actualSource.getFormatter().getRelay();

        DataFormat newParser = buildParser(dataFormatName);

        //Neuen Parser mit Relay koppeln
        newParser.setRelay(distributor);

        //Parser mit Datenquelle verbinden
        actualSource.setFormatter(newParser);
    }

    public void setDataSource(String dataSourceName) throws ClassNotFoundException{
        DataFormat actualParser = actualSource.getFormatter();

        actualSource = buildDataSource(dataSourceName);

        //Nur Parser wieder koppeln, da der Parser bereits mit dem Relay gekoppelt ist
        actualSource.setFormatter(actualParser);
    }

    protected DataFormat buildParser(String dataFormatName) throws ClassNotFoundException{
        Object dataFormat = Class.forName(dataFormatName);
        
        if(!(dataFormat instanceof DataSource)){
            //TODO: Absprechen ob eigene Exception dafür, evtl. sauberer
            throw new ClassNotFoundException();
        } else{
            return (DataFormat) dataFormat;
        }
    }

    protected DataSource buildDataSource(String dataSourceName) throws ClassNotFoundException {
        Object dataSource = Class.forName(dataSourceName);
        
        if(!(dataSource instanceof DataSource)){
            //TODO: Absprechen ob eigene Exception dafür, evtl. sauberer
            throw new ClassNotFoundException();
        } else{
            return (DataSource) dataSource;
        }
    }

    public List<String> getAvailableDataSources() {
        return convertClassNamesToString(ClassFinder.find(SOURCES_PACKAGE_NAME));
    }

    private List<String> convertClassNamesToString(List<Class<?>> classNames) {
        //Klassen als Stringnamen zurückliefern
        return classNames.stream()
                .map(c -> c.toString())
                .collect(Collectors.toList());
    }

    public List<String> getAvailableDataFormats() {
        return convertClassNamesToString(ClassFinder.find(PARSER_PACKAGE_NAME));
    }

    public void registerDataReceiver(Receiver receiver) {
    }

    public void unregisterDataReceiver(Receiver receiver) {

    }
}
