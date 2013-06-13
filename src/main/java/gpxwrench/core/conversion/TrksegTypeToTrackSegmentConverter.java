package gpxwrench.core.conversion;

import gpxwrench.core.domain.TrackSegment;

import org.springframework.core.convert.converter.Converter;

import com.topografix.gpx.TrksegType;


/**
 * Converts {@link TrksegType} gpx objects to {@link TrackSegment} domain objects.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 12, 2013
 */
public class TrksegTypeToTrackSegmentConverter implements Converter<TrksegType, TrackSegment> {

    /* 
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public TrackSegment convert(TrksegType source) {
        if (source == null) {
            return null;
        }
        /*
         * Note: right now, TrackSegment is just a holder for TrackPoints so there's
         * nothing really to the conversion. In the future, it might hold more attributes.
         */
        TrackSegment target = new TrackSegment();
        return target;
    }

}
