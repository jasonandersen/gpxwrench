package gpxwrench.core.measurement;

import java.math.BigDecimal;


/**
 * A measurement of distance.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 7, 2013
 */
public class Distance {
    
	private final static String TO_STRING = "%s %s";
	
    private final BigDecimal value;
    
    private final DistanceUnit unit;
    
    /**
     * Constructor.
     * @param value
     * @param unit
     */
    public Distance(BigDecimal value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }
    
    public DistanceUnit getUnit() {
        return unit;
    }
    
    public String toString() {
    	return String.format(TO_STRING, value.toString(), unit);
    }
}
