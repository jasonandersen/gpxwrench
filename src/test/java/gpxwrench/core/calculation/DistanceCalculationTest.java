package gpxwrench.core.calculation;

import static org.junit.Assert.assertEquals;
import gpxwrench.core.Constants;
import gpxwrench.core.measurement.Distance;
import gpxwrench.core.measurement.DistanceUnit;
import gpxwrench.core.position.Position;
import gpxwrench.core.position.PositionFactory;

import org.junit.Test;


/**
 * Validate distance calculations.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 17, 2013
 */
public class DistanceCalculationTest {
    
    private Calculations calc = new Calculations();
    
    private PositionFactory factory = new PositionFactory();
    
    private Position posA;
    
    private Position posB;
    
    private Distance distance;
    
    /**
     * Assert distance on a single degree of latitude is equal to 60 nautical miles.
     */
    @Test
    public void testOneDegreeLatitude() {
    	final double ACCEPTABLE_DELTA = 10.0; //allow a 10 meter delta
        posA = factory.createPosition("45", "-122", "0");
        posB = factory.createPosition("46", "-122", "0");
        distance = calc.distance(posA, posB);
        double expectedDistance = DistanceUnit.NAUTICAL_MILE.getMeters() * Constants.NM_PER_DEG_LAT;
        assertEquals(expectedDistance, distance.getValue(), ACCEPTABLE_DELTA);
    }
    
    /*
     * test with null values
     */
    
}
