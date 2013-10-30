package gpxwrench.core.calculation;

import gpxwrench.core.position.Position;

/**
 * Calculates the bearing between two points.
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @since Sep 4, 2013
 */
public interface BearingCalculator {

    /**
     * Calculates the bearing from point a to point b
     * @param a
     * @param b
     * @return the bearing in degrees from true north
     */
    public double bearing(Position a, Position b);
}
