package gpxwrench.core.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.topografix.gpx.GpxType;
import com.topografix.gpx.TrkType;
import com.topografix.gpx.TrksegType;
import com.topografix.gpx.WptType;

import gpxwrench.core.domain.Track;
import gpxwrench.core.domain.TrackPoint;
import gpxwrench.core.domain.TrackSegment;
import gpxwrench.core.exception.NoTracksFoundException;
import gpxwrench.core.service.GpxParserService;
import gpxwrench.core.service.GpxSerializationService;

/**
 * Implementation of the {@link GpxParserService} interface.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 9, 2013
 */
@Service
public class GpxParserServiceImpl implements GpxParserService {
    
    private final GpxSerializationService serializationService;
    
    private final ConversionService conversionService;
    
    /**
     * Constructor.
     * @param serializationService
     * @param conversionService
     */
    @Autowired
    public GpxParserServiceImpl(GpxSerializationService serializationService, ConversionService conversionService) {
        this.serializationService = serializationService;
        this.conversionService = conversionService;
    }
    
    /* 
     * @see gpxwrench.core.service.GpxParserService#parseTracks(java.io.File)
     */
    public Collection<Track> parseTracks(File gpxFile) throws NoTracksFoundException {
        Validate.notNull(gpxFile);
        /*
         * deserialize
         * loop through tracks
         */
        GpxType gpx = serializationService.deserialize(gpxFile);
        List<TrkType> gpxTracks = gpx.getTrk();
        if (gpxTracks == null || gpxTracks.isEmpty()) {
            throw new NoTracksFoundException(gpx);
        }
        List<Track> tracks = new ArrayList<Track>();
        for (TrkType gpxTrack : gpxTracks) {
            gpxTrack.setGpx(gpx);
            gpxTrack.setFileName(gpxFile.getName());
            tracks.add(parseTrack(gpxTrack));
        }
        return tracks;
    }
    
    /**
     * Parses a single GPX track.
     * @param gpxTrack
     * @return a {@link Track} object, never null
     */
    private Track parseTrack(TrkType gpxTrack) {
        Validate.notNull(gpxTrack);
        Track track = conversionService.convert(gpxTrack, Track.class);
        List<TrksegType> gpxSegments = gpxTrack.getTrkseg();
        for (TrksegType gpxSegment : gpxSegments) {
            TrackSegment segment = parseTrackSegment(gpxSegment);
            track.getSegments().add(segment);
        }
        return track;
    }

    /**
     * Parses a single GPX track segment.
     * @param gpxSegment
     * @return a {@link TrackSegment} object, never null
     */
    private TrackSegment parseTrackSegment(TrksegType gpxSegment) {
        Validate.notNull(gpxSegment);
        TrackSegment segment = conversionService.convert(gpxSegment, TrackSegment.class);
        List<WptType> gpxPoints = gpxSegment.getTrkpt();
        int sequence = 1;
        for (WptType gpxPoint : gpxPoints) {
            gpxPoint.setSequence(sequence++);
            TrackPoint point = conversionService.convert(gpxPoint, TrackPoint.class);
            segment.getTrackPoints().add(point);
        }
        return segment;
    }

}
