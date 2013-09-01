package gpxwrench.core.calculation;

import gpxwrench.core.measurement.Distance;
import gpxwrench.core.measurement.DistanceUnit;
import gpxwrench.core.position.Position;

import org.springframework.stereotype.Component;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

/**
 * Uses the simplelatlng library to calculate distances.
 * @author Jason Andersen (andersen.jason@gmail.com)
 *
 */
@Component
public class DistanceCalculatorSimpleLatLonImpl implements DistanceCalculator {
	
	/*
	 * @see gpxwrench.core.measurement.DistanceCalculator#calculate(gpxwrench.core.position.Position, gpxwrench.core.position.Position)
	 */
	public Distance calculate(Position pointA, Position pointB) {
		if (pointA == null || pointB == null) {
			return Distance.ZERO_METERS;
		}
		LatLng latlngA = new LatLng(pointA.getLatitude(), pointA.getLongitude());
		LatLng latlngB = new LatLng(pointB.getLatitude(), pointB.getLongitude());
		double distance = LatLngTool.distance(latlngA, latlngB, LengthUnit.METER);
		return new Distance(distance, DistanceUnit.METER);
	}

}
