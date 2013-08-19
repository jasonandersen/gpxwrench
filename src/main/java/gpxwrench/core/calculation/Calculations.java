package gpxwrench.core.calculation;


import org.apache.commons.lang3.Validate;

import gpxwrench.core.Constants;
import gpxwrench.core.domain.TrackPoint;
import gpxwrench.core.measurement.Distance;
import gpxwrench.core.measurement.DistanceUnit;
import gpxwrench.core.measurement.Velocity;
import gpxwrench.core.measurement.VelocityUnit;
import gpxwrench.core.position.Position;


/**
 * This class performs mathmatical calculations on geometric points.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 16, 2013
 */
public class Calculations {
    
    /**
     * Calculates the distance between two positions.
     * @param a
     * @param b
     * @return the non-negative distance in meters between two points, if either position
     *      is null, will return zero.
     */
    public Distance distance(Position a, Position b) {
        //TODO - implement distance method
    	double distance = distance(
    			a.getLatitude(), b.getLatitude(), 
    			a.getLongitude(), b.getLongitude(), 
    			a.getAltitude().getValue(), b.getAltitude().getValue());
    	return new Distance(distance, DistanceUnit.METER);
    }


	/*
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * 
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     */
    private double distance(double lat1, double lat2, double lon1, double lon2,
            double el1, double el2) {


        Double latDistance = convertDegreesToRadians(lat2 - lat1);
        Double lonDistance = convertDegreesToRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(convertDegreesToRadians(lat1)) * Math.cos(convertDegreesToRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = Constants.EARTH_RADIUS_KM * c * 1000; // convert to meters

        double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }
    
    /**
     * Converts degrees into radians
     * @param deg
     * @return
     */
    private double convertDegreesToRadians(double deg) {
    	return (deg * Math.PI) / 180.0;
    }

    
    
    
    
    /**
     * Calculates the average velocity between two track points.
     * @param begin
     * @param end
     * @return positive velocity in meters/second
     */
    public Velocity velocity(TrackPoint begin, TrackPoint end) {
        Validate.notNull(begin);
        Validate.notNull(end);
        Validate.notNull(begin.getTimestamp());
        Validate.notNull(end.getTimestamp());
        
        long beginMillis = begin.getTimestamp().getTimeInMillis();
        long endMillis = end.getTimestamp().getTimeInMillis();
        long durationInMillis = endMillis - beginMillis;
        if (durationInMillis == 0L) {
            //avoid the divide by zero error
            return new Velocity(0, VelocityUnit.METERS_PER_SECOND);
        }
        
        double durationInSeconds = (double)durationInMillis / 1000.0;
        
        Distance distance = distance(begin, end);
        double velocityMps =  distance.getValue() / durationInSeconds;
        return new Velocity(velocityMps, VelocityUnit.METERS_PER_SECOND);
    }
    
    /**
     * Calculates the bearing in degrees from <code>begin</code> to <code>end</code>.
     * @param begin
     * @param end
     * @return positive bearing in degrees
     * @throws IllegalArgumentException when either parameter is null
     */
    public double bearing(Position begin, Position end) {
    	
    	//TODO - implement bearing
    	
        return -1;
    }
    
    /**
     * Calculates the perpendicular distance the <code>mid</code> position sits off the line
     * running from <code>start</code> to </code>end</code>.
     * @param start
     * @param mid
     * @param end
     * @return
     */
    public Distance deflection(Position start, Position mid, Position end) {
    	
    	//TODO - implement deflection
    	
        return null;
    }
    
    /**
     * Calculates the significance of the <code>target</code> point within the track segment
     * defined from the <code>start</code> point to the <code>end</code> point.
     * @param start
     * @param target
     * @param end
     * @return a positive value indicating the significance to the route of the <code>target</code> point
     */
    public double significance(Position start, Position target, Position end) {
        /*
         * The significance of a point over a span defined from start to end is considered to be:
         * 
         *      deflection
         *      ----------
         *      sqrt(span)
         */
        Distance span = distance(start, end);
        if (span.getValue() == 0) {
            return 0;
        }
        Distance deflection = deflection(start, target, end);
        double spanRoot = Math.sqrt(span.getValue());
        return deflection.getValue() / spanRoot;
    }
}
