package module6;

import java.util.Collection;

/**
 * An implementation of a goodnessOfFitCalculator providing the `goodnessOfFit`
 * method that returns the chi squared value which describes how well a Theory
 * object fits a collection of data points.
 */
public class ChiSquared implements GoodnessOfFitCalculator {

	/**
	 * Calculates and returns a chi squared value determining how well the
	 * function represented by the Theory object fits the collection of data
	 * points.
	 * @param data collection of data points that provide the x and y
	 *        coordinates as well as the error on the y measurement
	 * @param theory object providing the y(x) method that represents the
	 *        function to be fit to the data
	 * @return the chi squared value
	 */
	@Override
	public double goodnessOfFit(Collection<DataPoint> data, Theory theory) {
		// sum the chi squared contributions of each data point
		double chisq = 0;
		for (DataPoint p : data) {
			// Difference between the y value of data point and theory at
			// position x
			double y_diff = p.getY() - theory.y(p.getX());
			chisq += Math.pow(y_diff / p.getEy(), 2);
		}
		return chisq;
	}

}
