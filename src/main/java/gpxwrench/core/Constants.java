package gpxwrench.core;

/**
 * This class contains numerical constants to avoid embedding magic numbers in code.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 16, 2013
 */
public class Constants {

    /**
     * Number of degrees in a complete circle.
     */
    public static final int MAX_DEGREES = 360;

    /**
     * The earth's radius in kilometers.
     */
    public static final long EARTH_RADIUS_KM = 6371;

    /**
     * The number of nautical miles in a single degree of latitude.
     */
    public static final long NM_PER_DEG_LAT = 60;

    /**
     * Typical acceptable delta when comparing two double values.
     */
    public static final double TEST_DELTA = 0.00001;
}
