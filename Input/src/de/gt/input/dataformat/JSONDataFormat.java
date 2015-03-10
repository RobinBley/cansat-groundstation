package de.gt.input.dataformat;

import de.gt.input.data.DataUnit;
import de.gt.input.sources.DataSource;
import java.util.ArrayList;

public class JSONDataFormat implements DataFormat {

    private final DataSource dataSource;
   
    /**
     * Datenobjekt wird uebergeben und zwischengespeichert
     * @param dataSource Ein Object, welches Daten als JSONObject enthaelt
     */
    public JSONDataFormat(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    
    @Override
    public boolean hasData() {
        return dataSource.hasData();
    }

    @Override
    public ArrayList<DataUnit> parseData() {
        if (hasData()){
            
            //JSONObject statt string
            String jobject = dataSource.nextData();
        }
        
        return null;
    }

}
