package gpxwrench.core.measurement;


/**
 * Measurement unit for distances.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 7, 2013
 */
@SuppressWarnings("javadoc")
public enum DistanceUnit {
    FOOT(-1.0),
    YARD(-1.0),
    METER(1.0),
    KILOMETER(1000.0),
    STATUTE_MILE(-1.0),
    NAUTICAL_MILE(1852.0);
    
    /**
     * A single unit of this measurement measured in meters
     */
    private final double distanceInMeters;
    
    /**
     * Constructor
     * @param distanceInMeters
     */
    DistanceUnit(double distanceInMeters) {
    	this.distanceInMeters = distanceInMeters;
    }
    
    /**
     * A single unit of this measurement measured in meters
     */
    public double getMeters() {
    	return distanceInMeters;
    }
}
