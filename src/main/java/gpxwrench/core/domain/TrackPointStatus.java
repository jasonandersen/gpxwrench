package gpxwrench.core.domain;


/**
 * Marks the status of a single {@link TrackPoint}.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 16, 2013
 */
public enum TrackPointStatus {
    /**
     * A valid track point
     */
    VALID(false),
    /**
     * A track point that is likely the result of a GPS error rather than a true position
     */
    OUTLIER(true),
    /**
     * A track point that was likely recorded while the vessel was not underway
     */
    STOP_CLUSTER(true);
    
    private final boolean ignore;
    
    /**
     * Private constructor
     * @param ignore
     */
    private TrackPointStatus(boolean ignore) {
        this.ignore = ignore;
    }
    
    /**
     * @return true if a track point of this status should be ignored during routine calculations
     */
    public boolean ignore() {
        return ignore;
    }
}
