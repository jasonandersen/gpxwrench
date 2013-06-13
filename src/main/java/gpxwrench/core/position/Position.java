package gpxwrench.core.position;

import gpxwrench.core.measurement.Distance;

import java.math.BigDecimal;


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
    BigDecimal getLatitude();
    
    /**
     * @return position's longitude coordinate
     */
    BigDecimal getLongitude();
    
    /**
     * @return position's altitude
     */
    Distance getAltitude();
}
