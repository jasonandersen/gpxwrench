package gpxwrench.core.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A GPS track.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class Track {
    
    private String name;
    
    private Calendar importDate;
    
    private String originalFileName;
    
    private String creator;
    
    private String originalName;
    
    private List<TrackSegment> segments = new ArrayList<TrackSegment>();

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public Calendar getImportDate() {
        return importDate;
    }

    
    public void setImportDate(Calendar importDate) {
        this.importDate = importDate;
    }

    
    public String getOriginalFileName() {
        return originalFileName;
    }

    
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    
    public String getCreator() {
        return creator;
    }

    
    public void setCreator(String creator) {
        this.creator = creator;
    }

    
    public String getOriginalName() {
        return originalName;
    }

    
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    
    public List<TrackSegment> getSegments() {
        return segments;
    }

    
    public void setSegments(List<TrackSegment> segments) {
        this.segments = segments;
    }
    
}
