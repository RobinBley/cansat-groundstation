package de.gt.gui.sources;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.sources.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stream data source
 *
 * @author mhuisi
 */
public class Stream implements DataSource{

    private DataFormat formatter;
    private final InputStream stream;
    private final byte delimiter;
    private final Charset charset;

    /**
     *
     * @param f - formatter to push data to
     * @param s - stream to read data from
     * @param delimiter - byte delimiter to delimit each datum
     * @param c - charset the data in the stream is streamed in
     */
    public Stream(InputStream s, byte delimiter, Charset c) {
        this.stream = s;
        this.delimiter = delimiter;
        this.charset = c;
    }

    @Override
    public void open() {
        try {
            Deque<Byte> segmentBuffer = new ArrayDeque<>();
            int b;
            while ((b = stream.read()) != -1) {
                segmentBuffer.add((byte) b);
                if (b == delimiter) {
                    int bufLen = segmentBuffer.size();
                    byte[] segmentBytes = new byte[bufLen];
                    for (int i = 0; i < bufLen; i++) {
                        segmentBytes[i] = segmentBuffer.remove();
                    }
                    String datum = new String(segmentBytes, charset);
                    formatter.parseData(datum);
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot read from stream!");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }

    @Override
    public void linkParser(DataFormat f) {
        this.formatter = f;
    }
}
