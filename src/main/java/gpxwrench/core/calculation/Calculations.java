package gpxwrench.core.calculation;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

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
    	double distanceDbl = distance(
    			a.getLatitude().doubleValue(), b.getLatitude().doubleValue(), 
    			a.getLongitude().doubleValue(), b.getLongitude().doubleValue(), 
    			a.getAltitude().getValue().doubleValue(), b.getAltitude().getValue().doubleValue());
    	BigDecimal distance = BigDecimal.valueOf(distanceDbl);
    	return new Distance(distance, DistanceUnit.METER);
    }
    
    
    /**
     * @return a distance object representing zero meters
     */
    private Distance zeroDistance() {
		return new Distance(BigDecimal.ZERO, DistanceUnit.METER);
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

        final int R = 6371; // Radius of the earth

        Double latDistance = convertDegreesToRadians(lat2 - lat1);
        Double lonDistance = convertDegreesToRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(convertDegreesToRadians(lat1)) * Math.cos(convertDegreesToRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }
    

	/*
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * 
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     */
    private BigDecimal distance(BigDecimal lat1, BigDecimal lat2, BigDecimal lon1, BigDecimal lon2,
            BigDecimal el1, BigDecimal el2) {

        final int R = 6371; // Radius of the earth in kilometers

        BigDecimal latDistance = convertDegreesToRadians(lat2.subtract(lat1));
        BigDecimal lonDistance = convertDegreesToRadians(lon2.subtract(lon1));
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(convertDegreesToRadians(lat1)) * Math.cos(convertDegreesToRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }
    
    
    /**
     * Converts degrees into radians
     * @param deg
     * @return
     */
    private BigDecimal convertDegreesToRadians(BigDecimal deg) {
    	BigDecimal pi = BigDecimal.valueOf(Math.PI);
        return deg.multiply(pi).divide(BigDecimal.valueOf(180.0));
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
            return new Velocity(BigDecimal.ZERO, VelocityUnit.METERS_PER_SECOND);
        }
        
        BigDecimal durationInSeconds = BigDecimal.valueOf((double)durationInMillis / (double)1000);
        
        Distance distance = distance(begin, end);
        BigDecimal velocityMps =  distance.getValue().divide(durationInSeconds);
        return new Velocity(velocityMps, VelocityUnit.METERS_PER_SECOND);
    }
    
    /**
     * Calculates the bearing in degrees from <code>begin</code> to <code>end</code>.
     * @param begin
     * @param end
     * @return positive bearing in degrees
     * @throws IllegalArgumentException when either parameter is null
     */
    public BigDecimal bearing(Position begin, Position end) {
    	
    	//TODO - implement bearing
    	
        return null;
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
    public BigDecimal significance(Position start, Position target, Position end) {
        /*
         * The significance of a point over a span defined from start to end is considered to be:
         * 
         *      deflection
         *      ----------
         *      sqrt(span)
         */
        Distance span = distance(start, end);
        if (span.getValue().compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        Distance deflection = deflection(start, target, end);
        BigDecimal numerator = deflection.getValue();
        Double spanRoot = Math.sqrt(span.getValue().doubleValue());
        BigDecimal divisor = BigDecimal.valueOf(spanRoot);
        return numerator.divide(divisor);
    }
}
