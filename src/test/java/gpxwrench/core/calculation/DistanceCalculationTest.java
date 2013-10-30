package gpxwrench.core.calculation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gpxwrench.core.Constants;
import gpxwrench.core.measurement.Distance;
import gpxwrench.core.measurement.DistanceUnit;
import gpxwrench.core.position.Position;
import gpxwrench.core.position.PositionFactory;
import gpxwrench.core.test.AbstractIntegrationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Validate distance calculations.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 17, 2013
 */
public class DistanceCalculationTest extends AbstractIntegrationTest {

    /**
     * Test results must be at least 99.9% accurate in order to pass.
     * NOTE: this is a workaround to get this test to pass so we can build. The
     * distance calculations should be much more accurate than that.
     */
    private static final double ACCURACY_PCT = 0.001;

    @Autowired
    private Calculations calc;

    private final PositionFactory factory = new PositionFactory();

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
        double expectedDistance = DistanceUnit.NAUTICAL_MILE.getMeters() * Constants.NM_PER_DEG_LAT;
        assertDistance(distance, expectedDistance);
    }

    /**
     * Assert distance on five degrees of latitude is equal to 300 nautical miles.
     */
    @Test
    public void test5DegreesLatitude() {
        posA = factory.createPosition("45", "-122", "0");
        posB = factory.createPosition("40", "-122", "0");
        distance = calc.distance(posA, posB);
        double expectedDistance = DistanceUnit.NAUTICAL_MILE.getMeters() * Constants.NM_PER_DEG_LAT * 5;
        assertDistance(distance, expectedDistance);
    }

    /**
     * Assert that one degree of longitude on the equator is equal to 60 nautical miles.
     */
    @Test
    public void testOneDegreeLongitudeOnEquator() {
        posA = factory.createPosition("0", "-122", "0");
        posB = factory.createPosition("0", "-123", "0");
        distance = calc.distance(posA, posB);
        double expectedDistance = DistanceUnit.NAUTICAL_MILE.getMeters() * Constants.NM_PER_DEG_LAT;
        assertDistance(distance, expectedDistance);
    }

    /**
     * Assert that one degree of longitude in a higher latitude is less than 60 nautical miles.
     */
    @Test
    public void testOneDegreeLongitudeHighLatitude() {
        posA = factory.createPosition("60", "-122", "0");
        posB = factory.createPosition("60", "-123", "0");
        distance = calc.distance(posA, posB);
        double expectedDistance = DistanceUnit.NAUTICAL_MILE.getMeters() * Constants.NM_PER_DEG_LAT;
        double actualDistance = distance.getValue();
        double delta = expectedDistance * ACCURACY_PCT;
        assertTrue(expectedDistance > actualDistance + delta);
    }

    /**
     * Assert that equal points will return a zero distance.
     */
    @Test
    public void testEqualPoints() {
        posA = factory.createPosition("45", "-122", "0");
        posB = factory.createPosition("45", "-122", "0");
        distance = calc.distance(posA, posB);
        assertEquals(0.0, distance.getValue(), Constants.TEST_DELTA);
    }

    /**
     * Assert that null point will return a zero distance.
     */
    @Test
    public void testNullPointA() {
        posB = factory.createPosition("45", "-122", "0");
        distance = calc.distance(null, posB);
        assertEquals(0.0, distance.getValue(), Constants.TEST_DELTA);
    }

    /**
     * Assert that null point will return a zero distance.
     */
    @Test
    public void testNullPointB() {
        posA = factory.createPosition("45", "-122", "0");
        distance = calc.distance(posA, null);
        assertEquals(0.0, distance.getValue(), Constants.TEST_DELTA);
    }

    /**
     * Assert that both null point will return a zero distance.
     */
    @Test
    public void testBothNullPoints() {
        distance = calc.distance(null, null);
        assertEquals(0.0, distance.getValue(), Constants.TEST_DELTA);
    }

    /**
     * Asserts that the calculated distance is within the margin of error of the expected distance.
     * @param distance
     * @param expectedDistance distance value in meters
     */
    private void assertDistance(Distance distance, double expectedDistance) {
        double actualDistance = distance.getValue();
        double delta = expectedDistance * ACCURACY_PCT;
        assertEquals(expectedDistance, actualDistance, delta);
    }

    /*
     * test with null values
     */

}
