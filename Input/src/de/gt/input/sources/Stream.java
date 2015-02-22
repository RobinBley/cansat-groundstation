package de.gt.input.sources;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stream data source
 * @author mhuisi
 */
public class Stream implements DataSource {

    private final Deque<String> segments = new ArrayDeque<>();
    private final Deque<Byte> segmentBuffer = new ArrayDeque<>();
    
    private final InputStream stream;
    private final byte delimiter;
    private final Charset charset;
    
    public Stream(InputStream s, byte delimiter, Charset c) {
        this.stream = s;
        this.delimiter = delimiter;
        this.charset = c;
    }
    
    @Override
    public String nextData() {
        return segments.remove();
    }

    @Override
    public boolean hasData() {
        int b;
        try {
            while ((b = stream.read()) != -1) {
                segmentBuffer.add((byte) b);
                if (b == delimiter) {
                    int bufLen = segmentBuffer.size();
                    byte[] segmentBytes = new byte[bufLen];
                    for (int i = 0; i < bufLen; i++) {
                        segmentBytes[i] = segmentBuffer.remove();
                    }
                    segments.add(new String(segmentBytes, charset));
                }
            }
        } catch (IOException e) {
            System.err.println("Cannot read from stream!");
            System.err.println(e.getMessage());
        }
        return !segments.isEmpty();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }

}
