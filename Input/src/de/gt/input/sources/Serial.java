package de.gt.input.sources;

import j.extensions.comm.SerialComm;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Data source for serial ports
 * @author mhuisi
 */
public class Serial implements DataSource {
    
    private final SerialComm port;
    private final Stream stream;
    
    public Serial(SerialComm p, byte delimiter, Charset c) {
        p.openPort();
        this.port = p;
        this.stream = new Stream(p.getInputStream(), delimiter, c);
    }
    
    public static Serial createInitialized(String portName, byte delimiter, Charset c) {
        SerialComm[] ports = SerialComm.getCommPorts();
        for (SerialComm p : ports) {
            if (p.getDescriptivePortName().equals(portName)) {
                return new Serial(p, delimiter, c);
            }
        }
        return null;
    }
    
    @Override
    public void close() throws IOException {
        port.closePort();
        stream.close();
    }
    
    @Override
    public String nextData() {
        return stream.nextData();
    }

    @Override
    public boolean hasData() {
        return stream.hasData();
    }
    
}
