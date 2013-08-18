package gpxwrench.core.position;

import gpxwrench.core.measurement.Distance;
import gpxwrench.core.measurement.DistanceUnit;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;


/**
 * Creates valid {@link Position} objects from various inputs.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 13, 2013
 */
@Service
public class PositionFactory {
    /*
     * NOTE: this class should be threadsafe.
     */
    
    private static final BigDecimal MAX_LATITUDE = new BigDecimal("90");
    
    private static final BigDecimal MAX_LONGITUDE = new BigDecimal("180");
    
    /**
     * Constructs a new {@link Position} object.
     * @param latitude must be parsed into a number >= -90 and <= 90
     * @param longitude must be parsed into a number >= -180 and <= 180
     * @param altitude defaults to distance in meters
     * @return a new {@link Position} object
     * @throws IllegalArgumentException when latitude is not valid
     * @throws IllegalArgumentException when longitude is not valid
     */
    public Position createPosition(String latitude, String longitude, String altitude) {
        BigDecimal lat = getLatitude(latitude);
        BigDecimal lon = getLongitude(longitude);
        Distance alt = getAltitude(altitude);
        return new SimplePosition(lat, lon, alt);
    }

    /**
     * Constructs a new {@link Position} object.
     * @param latitude must be >= -90 and <= 90
     * @param longitude must be >= -180 and <= 180
     * @param altitude defaults to distance in meters
     * @return a new {@link Position} object
     * @throws IllegalArgumentException when latitude is not valid
     * @throws IllegalArgumentException when longitude is not valid
     */
    public Position createPosition(BigDecimal latitude, BigDecimal longitude, BigDecimal altitude) {
        validateLatitude(latitude);
        validateLongitude(longitude);
        Distance alt = getAltitude(altitude);
        return new SimplePosition(latitude, longitude, alt);
    }

    /**
     * @param altitude
     * @return a valid {@link Distance} object representing altitude in meters
     */
    private Distance getAltitude(BigDecimal altitude) {
        return new Distance(altitude, DistanceUnit.METER);
    }
    
    /**
     * @param altitude
     * @return a valid {@link Distance} object representing altitude in meters
     */
    private Distance getAltitude(String altitude) {
        BigDecimal value = new BigDecimal(altitude);
        return new Distance(value, DistanceUnit.METER);
    }

    /**
     * @param longitude
     * @return a valid {@link BigDecimal} object representing longitude in degrees
     */
    private BigDecimal getLongitude(String longitude) {
        BigDecimal lon = new BigDecimal(longitude);
        validateLongitude(lon);
        return new BigDecimal(longitude);
    }
    
    /**
     * Validates longitude
     * @param longitude
     * @throws IllegalArgumentException when longitude is not valid
     */
    private void validateLongitude(BigDecimal longitude) {
        if (longitude.abs().compareTo(MAX_LONGITUDE) > 0) {
            String msg = String.format(
                    "longitude (%1$s) cannot be greater than %2$s or less than -%2$s", longitude, MAX_LONGITUDE);
            throw new IllegalArgumentException(msg);
        }
    }
    
    /**
     * @param latitude
     * @return a valid {@link BigDecimal} object representing latitude in degrees
     */
    private BigDecimal getLatitude(String latitude) {
        BigDecimal lat = new BigDecimal(latitude);
        validateLatitude(lat);
        return lat;
    }
    
    /**
     * Validates latitude
     * @param latitude
     * @throws IllegalArgumentException when latitude is not valid
     */
    private void validateLatitude(BigDecimal latitude) {
        if (latitude.abs().compareTo(MAX_LATITUDE) > 0) {
            String msg = String.format(
                    "latitude (%1$s) cannot be greater than %2$s or less than -%2$s", latitude, MAX_LATITUDE);
            throw new IllegalArgumentException(msg);
        }
    }

}
