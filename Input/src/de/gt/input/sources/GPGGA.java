package de.gt.input.sources;

import java.text.DecimalFormat;

/**
 * Represents the data contained
 * in GPGGA
 * @author mhuisi
 */
public final class GPGGA {
    
    private static final DecimalFormat latitudeFmt = new DecimalFormat("0000.0000");
    private static final DecimalFormat longitudeFmt = new DecimalFormat("00000.0000");
    
    private final double latitude;
    private final double longitude;
    private final double altitude;
    
    public GPGGA(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }
    
    /**
     * Creates a GPGGA gps value from a GPGGA gps string
     * @param gps - gps string
     * @return GPGGA
     */
    public static GPGGA createFromString(String gps) {
        String[] values = gps.split(",");
        if (values.length < 10) {
            throw new IllegalArgumentException("Invalid GPS-string");
        }
        double latitude = Double.valueOf(values[2]);
        double longitude = Double.valueOf(values[4]);
        double altitude = Double.valueOf(values[9]);
        return new GPGGA(latitude, longitude, altitude);
    }
    
    @Override
    public String toString() {
        return String.format("$GPGGA,125000.0,%s,N,%s,E,1,3,0.95,%s,M,17.8,M,,*65", 
                latitudeFmt.format(latitude), longitudeFmt.format(longitude), altitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }
    
}
