package gpxwrench.core.measurement;



/**
 * A measurement of distance.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 7, 2013
 */
public class Distance {
    
	/**
	 * Shortcut constant to create a zero meter distance value
	 */
	public final static Distance ZERO_METERS = new Distance(0, DistanceUnit.METER);
	
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
