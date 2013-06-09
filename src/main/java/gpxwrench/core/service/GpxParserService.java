package gpxwrench.core.service;

import gpxwrench.core.domain.Track;
import gpxwrench.core.exception.NoTracksFoundException;

import java.io.File;
import java.util.Collection;


/**
 * Service to parse GPX files into domain objects.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public interface GpxParserService {
    
    /**
     * Parses a GPX file into tracks, stores the tracks in the local DB and returns them
     * in a collection
     * @param gpxFile
     * @return a collection of tracks, will never return null and will never return an
     *      empty collection
     * @throws NoTracksFoundException when no tracks are discovered in the GPX file
     */
    Collection<Track> parseTracks(File gpxFile) throws NoTracksFoundException;
}
