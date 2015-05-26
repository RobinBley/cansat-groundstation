package de.gt.gui.sources;

import de.gt.api.input.dataformat.DataFormat;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import de.gt.api.sources.DataSource;
import j.extensions.comm.SerialComm;

/**
 * Datenquelle für serielle Ports
 *
 * @author mhuisi
 */
public class Serial implements DataSource {

    private final SerialComm port;
    private final Stream stream;

    /**
     * Konstruktor
     *
     * @param f - Formatter, zu dem Daten gepusht werden
     * @param p - Port, von dem Daten bezogen werden sollen
     * @param delimiter - Trenncharacter zwischen Datensätzen
     * @param c - Zeichensatz der gestreamten Daten
     */
    private Serial(SerialComm p, byte delimiter, Charset c) {
        this.port = p;
        this.stream = new Stream(p.getInputStream(), delimiter, c);
    }

    /**
     * Erstellt eine serielle Quelle von einem Port-Namen
     *
     * @param f - Formatter, zu dem Daten gepusht werden
     * @param portName - Portname
     * @param delimiter - Trenncharacter zwischen Datensätzen
     * @param c - Zeichensatz der gestreamten Daten
     * @return serielle Quelle
     */
    public static Serial createFromName(String portName, byte delimiter, Charset c) {
        return Arrays.stream(SerialComm.getCommPorts())
                .filter(p -> p.getDescriptivePortName().equals(portName))
                .map(p -> new Serial(p, delimiter, c))
                .findAny()
                .orElse(null);
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
