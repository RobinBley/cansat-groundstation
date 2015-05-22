package de.gt.gui.window;

import de.gt.api.datapipeline.DataPipeline;
import de.gt.api.relay.Configurable;
import de.gt.api.relay.Receiver;
import javax.swing.Action;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Item;
import org.openide.util.Lookup.Template;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

/**
 *
 * @author Kevin
 */
public abstract class DataReceiverComponent extends TopComponent implements Configurable, Receiver {

    protected DataPipeline pipeline;

    public DataReceiverComponent() {
        //Datapipeline Service über Lookup laden
        DataPipeline pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    @Override
    protected void componentOpened() {
        pipeline.registerDataReceiver(this);
    }

    @Override
    protected void componentClosed() {
        pipeline.unregisterDataReceiver(this);
    }
}
