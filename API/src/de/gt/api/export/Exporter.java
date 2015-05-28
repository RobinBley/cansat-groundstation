package de.gt.api.export;

/**
 * Stellt einen Exporter dar
 * @author Kevin
 */
public interface Exporter {
    /**
     * Name des Exporters
     * @return name
     */
    public String getExporterName();
    
    /**
     * Dateiendung der Datei im Exporter
     * @return Endung
     */
    public String getFileExt();
}
