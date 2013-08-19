package gpxwrench.core.measurement;



/**
 * A measurement of distance.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 7, 2013
 */
public class Distance {
    
	private final static String TO_STRING = "%d %s";
	
    private final double value;
    
    private final DistanceUnit unit;
    
    /**
     * Constructor.
     * @param value
     * @param unit
     */
    public Distance(double value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }
    
    public DistanceUnit getUnit() {
        return unit;
    }
    
    public String toString() {
    	return String.format(TO_STRING, value, unit);
    }
}
