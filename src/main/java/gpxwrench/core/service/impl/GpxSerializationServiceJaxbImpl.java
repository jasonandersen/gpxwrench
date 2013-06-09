package gpxwrench.core.service.impl;

import gpxwrench.core.service.GpxSerializationService;

import java.io.File;

import javax.xml.bind.JAXB;

import org.springframework.stereotype.Service;

import com.topografix.gpx.GpxType;


/**
 * An implementation of the {@link GpxSerializationService} interface using JAXB.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 8, 2013
 */
@Service
public class GpxSerializationServiceJaxbImpl implements GpxSerializationService {

    /* 
     * @see gpxwrench.core.service.GpxXmlParserService#parse(java.io.File)
     */
    public GpxType deserialize(File gpxFile) {
        return JAXB.unmarshal(gpxFile, GpxType.class);
    }

}
