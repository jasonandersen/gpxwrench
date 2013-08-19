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
     * Assert distance on a single degree of latitude is equal to 60 nautical miles.
     */
    @Test
    public void testOneDegreeLatitude() {
        posA = factory.createPosition("45", "-122", "0");
        posB = factory.createPosition("46", "-122", "0");
        distance = calc.distance(posA, posB);
        BigDecimal expectedDistance = new BigDecimal(1852 * 60);
        System.out.println(distance.toString());
        System.out.println(expectedDistance + " METERS");
        assertTrue(distance.getValue().compareTo(expectedDistance) == 0);
    }
    
    /*
     * test with null values
     */
    
}
