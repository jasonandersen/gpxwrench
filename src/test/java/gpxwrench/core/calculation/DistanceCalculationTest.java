package gpxwrench.core.calculation;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import gpxwrench.core.measurement.Distance;
import gpxwrench.core.position.Position;
import gpxwrench.core.position.PositionFactory;


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
     * Assert distance on a single degree of latitude.
     */
    @Test
    public void testOneDegreeLatitude() {
        posA = factory.createPosition("45", "-122", "0");
        posB = factory.createPosition("46", "-122", "0");
        distance = calc.distance(posA, posB);
        BigDecimal expectedDistance = new BigDecimal(1852);
        assertTrue(distance.getValue().compareTo(expectedDistance) == 0);
    }
    
}
