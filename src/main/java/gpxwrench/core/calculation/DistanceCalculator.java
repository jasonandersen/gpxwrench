package gpxwrench.core.calculation;

import gpxwrench.core.measurement.Distance;
import gpxwrench.core.position.Position;

/**
 * Calculates distance between two points.
 * 
 * @author Jason Andersen andersen.jason@gmail.com
 */
public interface DistanceCalculator {

    /**
     * Calculates the distance between point A and point B
     * @param pointA
     * @param pointB
     * @return a non-negative distance, if either point is null will return a 
     * 		zero distance
     */
    public Distance calculate(Position pointA, Position pointB);

}
