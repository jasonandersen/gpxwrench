package gpxwrench.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gpxwrench.core.service.impl.GpxSerializationServiceJaxbImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.topografix.gpx.GpxType;
import com.topografix.gpx.TrkType;
import com.topografix.gpx.TrksegType;
import com.topografix.gpx.WptType;


/**
 * Testing the {@link GpxSerializationService} implementation of the <code>deserialize</code> method.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class GpxSerializationServiceDeserializeTest {
    
    private GpxSerializationService service;
    
    private GpxType gpx;
    
    /**
     * Setup the GPX file
     * @throws IOException
     */
    @Before
    public void setup() throws IOException {
        service = new GpxSerializationServiceJaxbImpl();
        ClassPathResource resource = new ClassPathResource(
                "/gpxwrench/core/service/GpxSerializationServiceDeserializeTest-file1.gpx");
        gpx = service.deserialize(resource.getFile());
    }
    
    /**
     * Make sure we can parse a GPX file.
     */
    @Test
    public void testDeserialize() {
        assertNotNull(gpx);
    }
    
    /**
     * Validate null input is handled properly.
     */
    @Test(expected=NullPointerException.class)
    public void testNullInput() {
        service.deserialize(null);
    }
    
    /**
     * Validate deserialized track.
     */
    @Test
    public void testTrack() {
        List<TrkType> tracks = gpx.getTrk();
        assertEquals(1, tracks.size());
        TrkType track = tracks.get(0);
        assertEquals("Test Track", track.getName());
    }
    
    /**
     * Validate deserialized segment.
     */
    @Test
    public void testSegment() {
        TrkType track = gpx.getTrk().get(0);
        List<TrksegType> segments = track.getTrkseg();
        assertEquals(1, segments.size());
        TrksegType segment = segments.get(0);
        assertNotNull(segment);
        assertEquals(10, segment.getTrkpt().size());
    }

    /**
     * Validate the first point deserialized.
     */
    @Test
    public void testPoint0() {
        WptType point = getTrackPoint(0);
        BigDecimal lat = new BigDecimal("56.459316");
        BigDecimal lon = new BigDecimal("-132.385025");
        BigDecimal alt = new BigDecimal("3");
        assertEquals("2013-06-06T16:49:50Z", point.getTime().toString());
        assertEquals(lat, point.getLat());
        assertEquals(lon, point.getLon());
        assertEquals(alt, point.getEle());
    }

    /**
     * Validate the last point deserialized.
     */
    @Test
    public void testPoint9() {
        WptType point = getTrackPoint(9);
        BigDecimal lat = new BigDecimal("56.454464");
        BigDecimal lon = new BigDecimal("-132.390594");
        BigDecimal alt = new BigDecimal("2.6");
        assertEquals("2013-06-06T16:54:22Z", point.getTime().toString());
        assertEquals(lat, point.getLat());
        assertEquals(lon, point.getLon());
        assertEquals(alt, point.getEle());
    }
    
    /**
     * @param index
     * @return the track point at a specified index
     */
    private WptType getTrackPoint(int index) {
        TrkType track = gpx.getTrk().get(0);
        TrksegType segment = track.getTrkseg().get(0);
        return segment.getTrkpt().get(index);
    }
    
}
