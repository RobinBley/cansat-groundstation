package de.gt.gui.sources;

import de.gt.api.sources.DataSource;
import de.gt.api.input.dataformat.DataFormat;
import java.io.IOException;
import java.util.Collection;
import org.json.JSONObject;

/**
 * Debug source generating JSON
 *
 * @author mhuisi
 */
public class DebugJSON implements DataSource {

    private DataFormat formatter;
    private final DebugGenerator gen;
    private boolean closed = true;

    public DebugJSON(DataFormat formatter, DebugGenerator gen) {
        this.formatter = formatter;
        this.gen = gen;
    }

    /**
     * Creates a debug json source with a default initialized DebugGenerator.
     *
     * @param formatter - formatter to push data to
     * @param keys - keys to generate debug data for
     * @return source
     */
    public static DebugJSON createWithDebugGenerator(DataFormat formatter, Collection<String> keys) {
        DebugGenerator gen = DebugGenerator.createWithKeys(keys);
        return new DebugJSON(formatter, gen);
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
