package gpxwrench.core.position;

import gpxwrench.core.measurement.Distance;



/**
 * A simple implementation of the {@link Position} interface. Instances of this
 * class should be created by calling {@link PositionFactory#createPosition()}.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 * @see PositionFactory
 */
public class SimplePosition implements Position {
    
    private final double latitude;
    
    private final double longitude;
    
    private final Distance altitude;
    
    /**
     * Protected constructor. To instantiate this class, use the {@link PositionFactory} class.
     * @param latitude
     * @param longitude
     * @param altitude
     */
    protected SimplePosition(double latitude, double longitude, Distance altitude) {
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /* 
     * @see gpxwrench.core.Position#getLatitude()
     */
    public double getLatitude() {
        return latitude;
    }

    /* 
     * @see gpxwrench.core.Position#getLongitude()
     */
    public double getLongitude() {
        return longitude;
    }

    /* 
     * @see gpxwrench.core.Position#getAltitude()
     */
    public Distance getAltitude() {
        return altitude;
    }

}
