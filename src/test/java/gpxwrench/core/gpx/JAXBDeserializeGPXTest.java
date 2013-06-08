package gpxwrench.core.gpx;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXB;

import org.junit.Test;

import com.topografix.gpx.GpxType;


/**
 * Testing JAXB deserialization.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class JAXBDeserializeGPXTest {
    
    private File gpxFile = new File("2013-06-06T16-49-50Z.gpx");
    
    private GpxType gpx;
    
    @Test
    public void test() {
        gpx = JAXB.unmarshal(gpxFile, GpxType.class);
        assertNotNull(gpx);
        
    }
}
