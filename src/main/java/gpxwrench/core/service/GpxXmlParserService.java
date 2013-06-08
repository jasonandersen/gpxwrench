package gpxwrench.core.service;

import java.io.File;

import com.topografix.gpx.GpxType;


/**
 * Parses GPX XML files into {@link GpxType} objects.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public interface GpxXmlParserService {
    
    /**
     * Parses a GPX XML file into a {@link GpxType} object.
     * @param gpxFile
     * @return a {@link GpxType} object, will never return null
     */
    GpxType parse(File gpxFile);
}
