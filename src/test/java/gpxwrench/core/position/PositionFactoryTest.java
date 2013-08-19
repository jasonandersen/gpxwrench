package gpxwrench.core.position;

import static org.junit.Assert.assertEquals;
import gpxwrench.core.Constants;
import gpxwrench.core.measurement.Distance;
import gpxwrench.core.measurement.DistanceUnit;
import gpxwrench.core.test.AbstractIntegrationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Tests the {@link PositionFactory#createPosition(String, String, String)} method.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 13, 2013
 */
public class PositionFactoryTest extends AbstractIntegrationTest {
    
    private Position pos;
    
    @Autowired
    private PositionFactory factory;
    
    /**
     * Test happy path for all three string arguments
     */
    @Test
    public void testStringLatitudeLongitudeAltitude() {
        pos = factory.createPosition("59.0", "-122.0", "10.0");
        assertEquals(59.0, pos.getLatitude(), Constants.TEST_DELTA);
        assertEquals(-122.0, pos.getLongitude(), Constants.TEST_DELTA);
        Distance altitude = pos.getAltitude();
        assertEquals(10.0, altitude.getValue(), Constants.TEST_DELTA);
        assertEquals(DistanceUnit.METER, altitude.getUnit());
    }
    
    /**
     * Test happy path for all three BigDecimal arguments
     */
    @Test
    public void testBigDecimalLatitudeLongitudeAltitude() {
        pos = factory.createPosition(59.0, -122.0, 10.0);
        assertEquals(59.0, pos.getLatitude(), Constants.TEST_DELTA);
        assertEquals(-122.0, pos.getLongitude(), Constants.TEST_DELTA);
        Distance altitude = pos.getAltitude();
        assertEquals(10.0, altitude.getValue(), Constants.TEST_DELTA);
        assertEquals(DistanceUnit.METER, altitude.getUnit());
    }

    /**
     * Test maximum positive value for latitude
     */
    @Test
    public void testMaxLatitude() {
        pos = factory.createPosition("90.0", "-122.0", "10.0");
        assertEquals(90.0, pos.getLatitude(), Constants.TEST_DELTA);
    }

    /**
     * Test minimum negative value for latitude
     */
    @Test
    public void testMinLatitude() {
        pos = factory.createPosition("-90.0", "-122.0", "10.0");
        assertEquals(-90.0, pos.getLatitude(), Constants.TEST_DELTA);
    }

    /**
     * Test what happens when we pass an invalid latitude.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testMaxLatitudeExceeded() {
        pos = factory.createPosition("90.1", "-122.0", "10.0");
    }

    /**
     * Test what happens when we pass an invalid latitude.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testMinLatitudeExceeded() {
        pos = factory.createPosition("-90.1", "-122.0", "10.0");
    }
    

    /**
     * Test maximum positive value for longitude
     */
    @Test
    public void testMaxLongitude() {
        pos = factory.createPosition("59.0", "180.0", "10.0");
        assertEquals(180.0, pos.getLongitude(), Constants.TEST_DELTA);
    }

    /**
     * Test minimum negative value for Longitude
     */
    @Test
    public void testMinLongitude() {
        pos = factory.createPosition("59.0", "-180.0", "10.0");
        assertEquals(-180.0, pos.getLongitude(), Constants.TEST_DELTA);
    }

    /**
     * Test what happens when we pass an invalid Longitude.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testMaxLongitudeExceeded() {
        pos = factory.createPosition("59.0", "180.1", "10.0");
    }

    /**
     * Test what happens when we pass an invalid Longitude.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testMinLongitudeExceeded() {
        pos = factory.createPosition("59.0", "-180.1", "10.0");
    }
    
    
    
}
