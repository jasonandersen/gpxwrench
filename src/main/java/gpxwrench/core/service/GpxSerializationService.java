package gpxwrench.core.service;

import java.io.File;

import com.topografix.gpx.GpxType;


/**
 * Deserializes GPX XML files into {@link GpxType} objects.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public interface GpxSerializationService {
    
    /**
     * Deserializes a GPX XML file into a {@link GpxType} object.
     * @param gpxFile
     * @return a {@link GpxType} object, will never return null
     */
    GpxType deserialize(File gpxFile);
}
