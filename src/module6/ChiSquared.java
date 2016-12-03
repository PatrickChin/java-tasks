package module6;

import java.util.Collection;

public class ChiSquared implements GoodnessOfFitCalculator {

	@Override
	public double goodnessOfFit(Collection<DataPoint> data, Theory theory) {
		double chisq = 0;

		// sum the chi squared contributions of each data point
		for (DataPoint p : data) {
			double x = p.getX();
			double y_theory = theory.y(x);
			double y_data = p.getY();
			double y_err = p.getEy();

			double y_diff = y_data - y_theory;

			// Add another term to chisq
			chisq += y_diff*y_diff / (y_err * y_err);
		}
		return chisq;
	}

}
