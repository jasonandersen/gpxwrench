package gpxwrench.core.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import gpxwrench.core.domain.Track;
import gpxwrench.core.exception.NoTracksFoundException;
import gpxwrench.core.test.AbstractIntegrationTest;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

/**
 * Tests the {@link GpxParserService#parseTracks(File)} method implementation with a 
 * larger file.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 10, 2013
 */
public class GpxParserServiceBigFileTest extends AbstractIntegrationTest {

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
        ClassPathResource resource = new ClassPathResource("GpxParserServiceBigFileTest-file1.gpx");
        gpxFile = resource.getFile();
        tracks = parserService.parseTracks(gpxFile);
    }

    /**
     * Assert that the tracks collection comes back non-empty
     */
    @Test
    @Ignore
    public void testParse() {
        assertNotNull(tracks);
        assertFalse(tracks.isEmpty());
    }

}
