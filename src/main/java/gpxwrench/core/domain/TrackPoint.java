package gpxwrench.core.domain;

import gpxwrench.core.measurement.Distance;
import gpxwrench.core.position.Position;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang3.Validate;


/**
 * A single point on a track.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class TrackPoint implements Position {
    
    private final Position position;
    
    private final Calendar timestamp;
    
    private final Integer sequence;
    
    /**
     * Constructor
     * @param position
     * @param timestamp
     * @param sequence
     */
    public TrackPoint(Position position, Calendar timestamp, Integer sequence) {
        Validate.notNull(position);
        Validate.notNull(timestamp);
        Validate.notNull(sequence);
        
        this.position = position;
        this.timestamp = timestamp;
        this.sequence = sequence;
    }

    /* 
     * @see gpxwrench.core.domain.Position#getLatitude()
     */
    public BigDecimal getLatitude() {
        return position.getLatitude();
    }

    /* 
     * @see gpxwrench.core.domain.Position#getLongitude()
     */
    public BigDecimal getLongitude() {
        return position.getLongitude();
    }

    /* 
     * @see gpxwrench.core.domain.Position#getAltitude()
     */
    public Distance getAltitude() {
        return position.getAltitude();
    }
    
    /**
     * @return the date/time of this track point
     */
    public Calendar getTimestamp() {
        return timestamp;
    }

    /**
     * @return the sequence of this track point within its parent track segment
     */
    public int getSequence() {
        return sequence;
    }

}
