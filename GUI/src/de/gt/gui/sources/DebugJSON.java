package de.gt.gui.sources;

import de.gt.api.sources.DataSource;
import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.sources.DataSourceConfigurationDialog;
import de.gt.gui.dialog.sources.DebugConfigurationDialog;
import java.io.IOException;
import java.util.Map;
import org.json.JSONObject;

/**
 * Debugquelle, welche JSON generiert
 *
 * @author mhuisi
 */
public class DebugJSON implements DataSource {

    private DataFormat formatter;
    private final DebugGenerator gen;
    private boolean closed = true;

    public DebugJSON(DebugGenerator gen) {
        this.gen = gen;
    }

    public static DataSourceConfigurationDialog getConfigurationDialog() {
        return new DebugConfigurationDialog();
    }

    @Override
    public void open() {
        closed = false;
        while (!closed) {
            JSONObject json = new JSONObject();
            gen.generate().entrySet().forEach(e -> json.put(e.getKey(), e.getValue()));
            formatter.parseData(json.toString());
        }
    }

    @Override
    public void close() throws IOException {
        closed = true;
    }

    @Override
    public void linkParser(DataFormat f) {
        this.formatter = f;
    }
}
