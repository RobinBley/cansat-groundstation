package de.gt.core.sources;

import de.gt.api.sources.DataSource;
import de.gt.api.input.data.DataUnit;
import de.gt.api.input.dataformat.DataFormat;
import java.io.IOException;
import org.json.JSONObject;

/**
 * Debug source generating JSON
 *
 * @author mhuisi
 */
public class DebugJSON implements DataSource {

    private DataFormat formatter;
    private final DebugGenerator gen;

    public DebugJSON(DataFormat formatter, DebugGenerator gen) {
        this.gen = gen;
        this.formatter = formatter;
    }

    /**
     * Creates a debug json source with a default initialized DebugGenerator.
     *
     * @param formatter - formatter to push data to
     * @return source
     */
    public static DebugJSON createWithDebugGenerator(DataFormat formatter) {
        DebugGenerator gen = DebugGenerator.createWithDebugKeys();
        return new DebugJSON(formatter, gen);
    }

    @Override
    public void open() {
        JSONObject json = new JSONObject();
        gen.generate().forEach(entry -> {
            String k = entry.getKey();
            DataUnit u = entry.getValue();
            json.put(k, u.getObjectValue());
        });
        formatter.parseData(json.toString());
    }

    @Override
    public void close() throws IOException {
        // Nothing to close
    }

    @Override
    public DataFormat getFormatter() {
        return formatter;
    }

    @Override
    public void setFormatter(DataFormat f) {
        this.formatter = f;
    }

}
