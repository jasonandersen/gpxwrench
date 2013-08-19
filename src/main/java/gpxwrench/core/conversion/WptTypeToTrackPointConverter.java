package gpxwrench.core.conversion;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import gpxwrench.core.domain.TrackPoint;
import gpxwrench.core.position.Position;
import gpxwrench.core.position.PositionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.topografix.gpx.WptType;


/**
 * Converts GPX {@link WptType} objects to {@link TrackPoint} domain objects.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 13, 2013
 */
public class WptTypeToTrackPointConverter implements Converter<WptType, TrackPoint> {
    
    private static final TimeZone DEFAULT_TZ = TimeZone.getTimeZone("GMT");
    
    @Autowired
    private PositionFactory positionFactory;
    
    /* 
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public TrackPoint convert(WptType source) {
        if (source == null) {
            return null;
        }
        Position position = positionFactory.createPosition(
        		source.getLat().doubleValue(), source.getLon().doubleValue(), source.getEle().doubleValue());
        GregorianCalendar timestamp = source.getTime().toGregorianCalendar(DEFAULT_TZ, Locale.getDefault(), null);
        Integer sequence = source.getSequence();
        return new TrackPoint(position, timestamp, sequence);
    }

}
