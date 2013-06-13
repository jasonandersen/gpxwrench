package gpxwrench.core.exception;

import com.topografix.gpx.GpxType;


/**
 * Thrown during the parsing of a GPX file when no tracks are found within
 * the GPX file.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class NoTracksFoundException extends Exception {
    
    /**
     * The parsed GPX object in which no tracks were found.
     */
    private final GpxType gpx;
    
    /**
     * Constructor
     * @param gpx
     */
    public NoTracksFoundException(GpxType gpx) {
        this.gpx = gpx;
    }

}
