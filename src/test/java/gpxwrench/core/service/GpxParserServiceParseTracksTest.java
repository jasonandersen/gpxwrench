package gpxwrench.core.service;

import static org.junit.Assert.*;
import gpxwrench.core.domain.Track;
import gpxwrench.core.domain.TrackPoint;
import gpxwrench.core.domain.TrackSegment;
import gpxwrench.core.exception.NoTracksFoundException;
import gpxwrench.core.measurement.Distance;
import gpxwrench.core.test.AbstractIntegrationTest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;


/**
 * Tests the {@link GpxParserService#parseTracks(File)} method implementation.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 10, 2013
 */
public class GpxParserServiceParseTracksTest extends AbstractIntegrationTest {
    
    @Autowired
    private GpxParserService parserService;
    
    private File gpxFile;
    
    private Collection<Track> tracks;
    
    /**
     * Run the parseTracks method for each test.
     * @throws IOException
     * @throws NoTracksFoundException
     */
    @Before
    public void setup() throws IOException, NoTracksFoundException {
        ClassPathResource resource = new ClassPathResource("/gpxwrench/core/service/GpxParserServiceParseTracksTest-file1.gpx");
        gpxFile = resource.getFile();
        tracks = parserService.parseTracks(gpxFile);
    }
    
    /**
     * Assert that the tracks collection comes back non-empty
     */
    @Test
    public void testParse() {
        assertNotNull(tracks);
        assertFalse(tracks.isEmpty());
        assertNotNull(tracks.iterator().next());
    }
    
    /**
     * Assert that the only track was properly parsed
     */
    @Test
    public void testTrack() {
        Track track = getFirstTrack();
        assertEquals("Test Track", track.getName());
        assertEquals("Test Track", track.getOriginalName());
        assertEquals("GpxParserServiceParseTracksTest-file1.gpx", track.getOriginalFileName());
        assertEquals("Bad Elf GPS Pro 2.0.48", track.getCreator());
        assertNotNull(track.getSegments());
        assertFalse(track.getSegments().isEmpty());
        //TODO check import date
    }
    
    /**
     * Assert that the only track segment was properly parsed
     */
    @Test
    public void testTrackSegment() {
        TrackSegment segment = getFirstTrackSegment();
        assertEquals(10, segment.getTrackPoints().size());
    }
    
    /**
     * Assert that the first point was parsed correctly
     */
    @Test
    public void testFirstTrackPoint() {
        /*
         * <trkpt lat="56.459316" lon="-132.385025">
         *     <ele>3</ele>
         *     <time>2013-06-06T16:49:50Z</time>
         *     <hdop>0.7</hdop>
         *     <extensions><badelf:speed>1.800554</badelf:speed></extensions>
         * </trkpt>
         */
        TrackPoint point = getTrackPoint(0);
        assertTrue(point.getLatitude().compareTo(new BigDecimal("56.459316")) == 0);
        assertTrue(point.getLongitude().compareTo(new BigDecimal("-132.385025")) == 0);
        Distance altitude = point.getAltitude();
        assertTrue(altitude.getValue().compareTo(new BigDecimal("3")) == 0);
        assertEquals(1, point.getSequence());
        
        //validate timestamp (holy crap, Java - could you make dates more difficult?)
        Calendar timestamp = point.getTimestamp();
        assertEquals(2013, timestamp.get(Calendar.YEAR));
        assertEquals(Calendar.JUNE, timestamp.get(Calendar.MONTH));
        assertEquals(6, timestamp.get(Calendar.DAY_OF_MONTH));
        assertEquals(TimeZone.getTimeZone("GMT"), timestamp.getTimeZone());
        assertEquals(16, timestamp.get(Calendar.HOUR_OF_DAY));
        assertEquals(49, timestamp.get(Calendar.MINUTE));
        assertEquals(50, timestamp.get(Calendar.SECOND));
    }
    
    /**
     * Assert that sequences are set properly for all track points
     */
    @Test
    public void testTrackPointSequences() {
        TrackSegment segment = getFirstTrackSegment();
        int expectedSequence = 1;
        for (TrackPoint point : segment.getTrackPoints()) {
            assertEquals(expectedSequence++, point.getSequence());
        }
    }
    
    /**
     * @return the first track
     */
    private Track getFirstTrack() {
        return tracks.iterator().next();
    }
    
    /**
     * @return the first track segment
     */
    private TrackSegment getFirstTrackSegment() {
        return getFirstTrack().getSegments().get(0);
    }
    
    /**
     * @return the track point at the specified index
     */
    private TrackPoint getTrackPoint(int index) {
        TrackSegment segment = getFirstTrackSegment();
        return segment.getTrackPoints().get(index);
    }
}
