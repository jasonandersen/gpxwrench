package gpxwrench.core.position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
        assertEquals(new BigDecimal("59.0"), pos.getLatitude());
        assertEquals(new BigDecimal("-122.0"), pos.getLongitude());
        Distance altitude = pos.getAltitude();
        assertTrue(altitude.getValue().compareTo(new BigDecimal("10")) == 0);
        assertEquals(DistanceUnit.METER, altitude.getUnit());
    }
    
    /**
     * Test happy path for all three BigDecimal arguments
     */
    @Test
    public void testBigDecimalLatitudeLongitudeAltitude() {
        pos = factory.createPosition(new BigDecimal("59.0"), new BigDecimal("-122.0"), new BigDecimal("10.0"));
        assertEquals(new BigDecimal("59.0"), pos.getLatitude());
        assertEquals(new BigDecimal("-122.0"), pos.getLongitude());
        Distance altitude = pos.getAltitude();
        assertTrue(altitude.getValue().compareTo(new BigDecimal("10")) == 0);
        assertEquals(DistanceUnit.METER, altitude.getUnit());
    }

    /**
     * Test maximum positive value for latitude
     */
    @Test
    public void testMaxLatitude() {
        pos = factory.createPosition("90.0", "-122.0", "10.0");
        assertEquals(new BigDecimal("90.0"), pos.getLatitude());
    }

    /**
     * Test minimum negative value for latitude
     */
    @Test
    public void testMinLatitude() {
        pos = factory.createPosition("-90.0", "-122.0", "10.0");
        assertEquals(new BigDecimal("-90.0"), pos.getLatitude());
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
        assertEquals(new BigDecimal("180.0"), pos.getLongitude());
    }

    /**
     * Test minimum negative value for Longitude
     */
    @Test
    public void testMinLongitude() {
        pos = factory.createPosition("59.0", "-180.0", "10.0");
        assertEquals(new BigDecimal("-180.0"), pos.getLongitude());
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
