package gpxwrench.core.domain;

import java.util.Calendar;


/**
 * A single point on a track.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class TrackPoint {
    
    private PositionImpl position;
    
    private Calendar timestamp;
    
    private int sequence;

    
    public PositionImpl getPosition() {
        return position;
    }

    
    public void setPosition(PositionImpl position) {
        this.position = position;
    }

    
    public Calendar getTimestamp() {
        return timestamp;
    }

    
    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    
    public int getSequence() {
        return sequence;
    }

    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    
    
    
}
