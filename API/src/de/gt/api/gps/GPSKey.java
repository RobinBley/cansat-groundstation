package de.gt.api.gps;

/**
 * Represents the sub keys
 * of GPS data
 * @author mhuisi
 */
public final class GPSKey {
    
    private final String latitudeKey;
    private final String longitudeKey;
    private final String altitudeKey;
    
    /**
     * Constructor
     * @param latitudeKey
     * @param longitudeKey
     * @param altitudeKey 
     */
    public GPSKey(String latitudeKey, String longitudeKey, String altitudeKey) {
        this.latitudeKey = latitudeKey;
        this.longitudeKey = longitudeKey;
        this.altitudeKey = altitudeKey;
    }

    public String getLatitudeKey() {
        return latitudeKey;
    }

    public String getLongitudeKey() {
        return longitudeKey;
    }

    public String getAltitudeKey() {
        return altitudeKey;
    }
    
}
