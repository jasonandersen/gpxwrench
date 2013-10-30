package gpxwrench.core.measurement;

/**
 * Measurement of velocity.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 16, 2013
 */
public class Velocity {

    /**
     * Shortcut constant to create a zero meters/second velocity.
     */
    public final static Velocity ZERO_MPS = new Velocity(0.0, VelocityUnit.METERS_PER_SECOND);

    private final double value;

    private final VelocityUnit unit;

    /**
     * Constructor
     * @param value
     * @param unit
     */
    public Velocity(double value, VelocityUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public VelocityUnit getUnit() {
        return unit;
    }

}
