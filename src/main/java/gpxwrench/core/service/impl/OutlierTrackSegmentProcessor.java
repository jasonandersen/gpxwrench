package gpxwrench.core.service.impl;

import gpxwrench.core.domain.TrackSegment;
import gpxwrench.core.service.TrackSegmentProcessor;


/**
 * Seeks out GPS outliers with a {@link TrackSegment} and marks them
 * as OUTLIER.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 16, 2013
 */
public class OutlierTrackSegmentProcessor implements TrackSegmentProcessor {

    /**
     * Evaluates all {@TrackPoint}s to determine if any are considered GPS outliers.
     */
    public void process(TrackSegment segment) {
        throw new UnsupportedOperationException();
    }

}
