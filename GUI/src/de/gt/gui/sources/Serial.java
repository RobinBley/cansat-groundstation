package de.gt.gui.sources;

import de.gt.api.input.dataformat.DataFormat;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import de.gt.api.sources.DataSource;
import j.extensions.comm.SerialComm;

/**
 * Data source for serial ports
 *
 * @author mhuisi
 */
public class Serial implements DataSource {
    private final SerialComm port;
    private final Stream stream;

    /**
     *
     * @param f - formatter to push data to
     * @param p - port to stream data from
     * @param delimiter - delimiter value between every datum in the stream
     * @param c - charset the data is streamed in
     */
    private Serial(SerialComm p, byte delimiter, Charset c) {
        this.port = p;
        this.stream = new Stream(p.getInputStream(), delimiter, c);
    }

    /**
     * Creates a serial source from a port name.
     *
     * @param f - formatter to push data to
     * @param portName
     * @param delimiter - delimiter value between every datum in the stream
     * @param c - charset the data is streamed in
     * @return serial source
     */
    public static Serial createFromName(String portName, byte delimiter, Charset c) {
        return Arrays.stream(SerialComm.getCommPorts())
                .filter(p -> p.getDescriptivePortName().equals(portName))
                .map(p -> new Serial(p, delimiter, c))
                .findAny().orElse(null);
    }

    @Override
    public void open() {
        port.openPort();
        stream.open();
    }

    @Override
    public void close() throws IOException {
        port.closePort();
        stream.close();
    }

    @Override
    public void linkParser(DataFormat f) {
        stream.linkParser(f);
    }

}
