package gpxwrench.core.domain;

import java.util.Calendar;
import java.util.List;


/**
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class Track {
    
    private String name;
    
    private Journey journey;
    
    private Calendar importDate;
    
    private String originalFileName;
    
    private TravelMode travelMode;
    
    private String creator;
    
    private String originalName;
    
    private List<TrackSegment> segments;
    
}
