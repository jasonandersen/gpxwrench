package gpxwrench.core.conversion;

import java.util.GregorianCalendar;
import java.util.TimeZone;

import gpxwrench.core.domain.Track;

import org.apache.commons.lang3.Validate;
import org.springframework.core.convert.converter.Converter;

import com.topografix.gpx.TrkType;


/**
 * Converts GPX {@link TrkType} objects to {@link Track} domain objects.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 10, 2013
 */
public class TrkTypeToTrackConverter implements Converter<TrkType, Track> {

    /* 
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Track convert(TrkType source) {
        if (source == null) {
            return null;
        }
        Validate.notNull(source.getGpx());
        Track target = new Track();
        target.setImportDate(new GregorianCalendar(TimeZone.getTimeZone("GMT")));
        target.setCreator(source.getGpx().getCreator());
        target.setName(source.getName());
        target.setOriginalName(source.getName());
        target.setOriginalFileName(source.getFileName());
        return target;
    }

}
