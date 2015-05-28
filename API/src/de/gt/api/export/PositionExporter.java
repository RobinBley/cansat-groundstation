package de.gt.api.export;

import de.gt.api.gps.GPSKey;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kevin
 */
public interface PositionExporter extends Exporter {

    public boolean exportData(Map<String, List<Double>> data, GPSKey k, File output);
}
