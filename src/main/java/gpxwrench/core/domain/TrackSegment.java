package gpxwrench.core.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A segment of {@link TrackPoints} within a {@link Track}.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class TrackSegment {
    
    private List<TrackPoint> trackPoints = new ArrayList<TrackPoint>();

    
    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    
    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }
    
}
