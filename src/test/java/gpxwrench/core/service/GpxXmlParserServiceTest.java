package gpxwrench.core.service;

import static org.junit.Assert.*;

import gpxwrench.core.service.impl.GpxXmlParserServiceJaxbImpl;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.topografix.gpx.GpxType;


/**
 * Testing the {@link GpxXmlParserService} implementation.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class GpxXmlParserServiceTest {

    private GpxXmlParserService service;
    
    private File gpxFile;
    
    @Before
    public void setup() {
        /*
         * TODO we should be using Spring to do this
         */
        service = new GpxXmlParserServiceJaxbImpl();
        gpxFile = new File("2013-06-06T16-49-50Z.gpx");
    }
    
    /**
     * Make sure we can parse a GPX file.
     */
    @Test
    public void testParse() {
        GpxType gpx = service.parse(gpxFile);
        assertNotNull(gpx);
    }
}
