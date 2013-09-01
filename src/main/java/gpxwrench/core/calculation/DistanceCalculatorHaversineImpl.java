/**
 * 
 */
package gpxwrench.core.calculation;

import gpxwrench.core.Constants;
import gpxwrench.core.measurement.Distance;
import gpxwrench.core.measurement.DistanceUnit;
import gpxwrench.core.position.Position;

import org.springframework.stereotype.Component;

/**
 * Distance calculator using the Haversine formula.
 * @author Jason Andersen (andersen.jason@gmail.com)
 * 
 * NOTE: currently using the {@link DistanceCalculatorSimpleLatLonImpl} implementation because
 * it's proven to be slightly more accurate.
 */
//@Component
public class DistanceCalculatorHaversineImpl implements DistanceCalculator {
	
	/*
	 * @see gpxwrench.core.measurement.DistanceCalculator#calculate(gpxwrench.core.position.Position, gpxwrench.core.position.Position)
	 */
	public Distance calculate(Position pointA, Position pointB) {
		if (pointA == null || pointB == null) {
			return Distance.ZERO_METERS;
		}
		/*
		 * TODO: there's an assumption here that altitudes are in meters - should verify
		 */
		double distance = distance(
				pointA.getLatitude(), pointB.getLatitude(), 
				pointA.getLongitude(), pointB.getLongitude(), 
				pointA.getAltitude().getValue(), pointB.getAltitude().getValue());
		return new Distance(distance, DistanceUnit.METER);
		
	}
	
	/**
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
}
