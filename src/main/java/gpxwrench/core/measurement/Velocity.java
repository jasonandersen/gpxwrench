package gpxwrench.core.measurement;

import java.math.BigDecimal;


/**
 * Measurement of velocity.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 16, 2013
 */
public class Velocity {
    
    private final BigDecimal value;
    
    private final VelocityUnit unit;
    
    /**
     * Constructor
     * @param value
     * @param unit
     */
    public Velocity(BigDecimal value, VelocityUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    public VelocityUnit getUnit() {
        return unit;
    }
    
}
