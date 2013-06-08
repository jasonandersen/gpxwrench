package gpxwrench.core.service.impl;

import gpxwrench.core.service.GpxXmlParserService;

import java.io.File;

import javax.xml.bind.JAXB;

import com.topografix.gpx.GpxType;


/**
 * An implementation of the {@link GpxXmlParserService} interface using JAXB.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
public class GpxXmlParserServiceJaxbImpl implements GpxXmlParserService {

    /* 
     * @see gpxwrench.core.service.GpxXmlParserService#parse(java.io.File)
     */
    public GpxType parse(File gpxFile) {
        /*
         * TODO use Apache Commons Validate.notNull() method
         */
        if (gpxFile == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        return JAXB.unmarshal(gpxFile, GpxType.class);
    }

}
