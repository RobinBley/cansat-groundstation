package de.gt.gui.window;

import de.gt.api.config.Config;
import de.gt.api.datapipeline.DataPipeline;
import de.gt.api.relay.Configurable;
import de.gt.api.relay.Receiver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;

/**
 *
 * @author Kevin
 */
public abstract class DataReceiverComponent extends TopComponent implements Configurable, Receiver {

    protected DataPipeline pipeline;

    public DataReceiverComponent() {
        //Datapipeline Service über Lookup laden
        pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    protected void componentOpened() {
        pipeline.registerDataReceiver(this);
    }

    @Override
    protected void componentClosed() {
        pipeline.unregisterDataReceiver(this);
    }

    public abstract void clearData();

    @Override
    public void configChanged(Config newConfig) {
        //Nicht alle Komponenten reagieren auf Config Änderungen
        return;
    }

    @Override
    public void imported(Map<String, List<Double>> importData) {
        //Alle Daten löschen
        clearData();

        List<Double> firstList = importData.values().stream().findFirst().get();

        Map<String, Double> datum = new HashMap<>();
        for (int i = 0; i < firstList.size(); i++) {
            datum.clear();

            for (String key : importData.keySet()) {
                for (List<Double> values : importData.values()) {
                    //Valuekey mit String key in datum Map verknüpfen
                    datum.put(key, values.get(i));
                }
            }

            this.receive(datum);
        }
    }
}
