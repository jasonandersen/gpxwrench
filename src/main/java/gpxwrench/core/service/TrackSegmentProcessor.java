package gpxwrench.core.service;

import gpxwrench.core.domain.TrackSegment;


/**
 * Performs a processing operation against a {@link TrackSegment} object.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 16, 2013
 */
public interface TrackSegmentProcessor {
    
    /**
     * Processes an individual {@link TrackSegment}
     * @param segment
     */
    void process(TrackSegment segment);
}
