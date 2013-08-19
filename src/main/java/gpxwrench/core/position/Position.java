package gpxwrench.core.position;

import gpxwrench.core.measurement.Distance;


/**
 * A three dimensional position on Earth.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 7, 2013
 */
public interface Position {
    
    /**
     * @return position's latitude coordinate
     */
    double getLatitude();
    
    /**
     * @return position's longitude coordinate
     */
    double getLongitude();
    
    /**
     * @return position's altitude
     */
    Distance getAltitude();
}
