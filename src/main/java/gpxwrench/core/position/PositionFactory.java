package gpxwrench.core.position;

import gpxwrench.core.measurement.Distance;
import gpxwrench.core.measurement.DistanceUnit;

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
    
    private static final double MAX_LATITUDE = 90.0;
    
    private static final double MAX_LONGITUDE = 180.0;
    
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
        double lat = getLatitude(latitude);
        double lon = getLongitude(longitude);
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
    public Position createPosition(double latitude, double longitude, double altitude) {
        validateLatitude(latitude);
        validateLongitude(longitude);
        Distance alt = getAltitude(altitude);
        return new SimplePosition(latitude, longitude, alt);
    }

    /**
     * @param altitude
     * @return a valid {@link Distance} object representing altitude in meters
     */
    private Distance getAltitude(double altitude) {
        return new Distance(altitude, DistanceUnit.METER);
    }
    
    /**
     * @param altitude
     * @return a valid {@link Distance} object representing altitude in meters
     */
    private Distance getAltitude(String altitude) {
        double value = Double.parseDouble(altitude);
        return new Distance(value, DistanceUnit.METER);
    }

    /**
     * @param longitude
     * @return a valid double representing longitude in degrees
     */
    private double getLongitude(String longitude) {
    	double lon = Double.parseDouble(longitude);
        validateLongitude(lon);
        return lon;
    }
    
    /**
     * Validates longitude
     * @param longitude
     * @throws IllegalArgumentException when longitude is not valid
     */
    private void validateLongitude(double longitude) {
        if (Math.abs(longitude) > MAX_LONGITUDE) {
            String msg = String.format(
                    "longitude (%1$s) cannot be greater than %2$s or less than -%2$s", longitude, MAX_LONGITUDE);
            throw new IllegalArgumentException(msg);
        }
    }
    
    /**
     * @param latitude
     * @return a valid double representing latitude in degrees
     */
    private double getLatitude(String latitude) {
    	double lat = Double.parseDouble(latitude);
        validateLatitude(lat);
        return lat;
    }
    
    /**
     * Validates latitude
     * @param latitude
     * @throws IllegalArgumentException when latitude is not valid
     */
    private void validateLatitude(double latitude) {
        if (Math.abs(latitude) > MAX_LATITUDE) {
            String msg = String.format(
                    "latitude (%1$s) cannot be greater than %2$s or less than -%2$s", latitude, MAX_LATITUDE);
            throw new IllegalArgumentException(msg);
        }
    }

}
