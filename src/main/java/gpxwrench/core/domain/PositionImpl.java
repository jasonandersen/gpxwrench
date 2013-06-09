package gpxwrench.core.domain;

import gpxwrench.core.measurement.Distance;

import java.math.BigDecimal;


/**
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class PositionImpl implements Position {
    
    private BigDecimal latitude;
    
    private BigDecimal longitude;
    
    private Distance altitude;

    /* 
     * @see gpxwrench.core.Position#getLatitude()
     */
    public BigDecimal getLatitude() {
        return null;
    }

    /* 
     * @see gpxwrench.core.Position#getLongitude()
     */
    public BigDecimal getLongitude() {
        return null;
    }

    /* 
     * @see gpxwrench.core.Position#getAltitude()
     */
    public Distance getAltitude() {
        return null;
    }

}
