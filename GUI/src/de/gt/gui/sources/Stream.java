package de.gt.gui.sources;

import de.gt.api.input.dataformat.DataFormat;
import de.gt.api.log.Out;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stream-Datenquelle
 *
 * @author mhuisi
 */
public class Stream {

    private DataFormat formatter;
    private final InputStream stream;
    private final byte delimiter;
    private final Charset charset;

    /**
     * Konstruktor
     * @param s - stream, von dem Daten gelesen werden sollen
     * @param delimiter - Trennzeichen zwischen Datensätzen
     * @param c - Zeichensatz der gestreamten Daten
     */
    public Stream(InputStream s, byte delimiter, Charset c) {
        this.stream = s;
        this.delimiter = delimiter;
        this.charset = c;
    }

    public void open() {
        if (this.formatter == null) {
            Out.log("Kein Formatter gesetzt.");
            return;
        }
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
            Out.log("Kann nicht von Stream lesen.");
        }
    }

    public void close() throws IOException {
        stream.close();
    }

    public void linkParser(DataFormat f) {
        this.formatter = f;
    }
}
