package gpxwrench.core.calculation;

import gpxwrench.core.Constants;
import gpxwrench.core.position.Position;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

/**
 * Calculates a great circle bearing.
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @since Sep 4, 2013
 */
@Service
public class BearingCalculatorGreatCircleImpl implements BearingCalculator {

    /**
     * @see gpxwrench.core.calculation.BearingCalculator#bearing(gpxwrench.core.position.Position, gpxwrench.core.position.Position)
     */
    @Override
    public double bearing(Position a, Position b) {
        Validate.notNull(a);
        Validate.notNull(b);

        double deltaLon = b.getLongitude() - a.getLongitude();
        double y = Math.sin(deltaLon) * Math.cos(b.getLatitude());
        double x = Math.cos(a.getLatitude()) * Math.sin(b.getLatitude())
                - Math.sin(a.getLatitude()) * Math.cos(b.getLatitude()) * Math.cos(deltaLon);
        double bearingRads = Math.atan2(y, x);
        double bearingDeg = Math.toDegrees(bearingRads);
        bearingDeg = (bearingDeg + Constants.MAX_DEGREES) % Constants.MAX_DEGREES;

        return bearingDeg;
    }
}
