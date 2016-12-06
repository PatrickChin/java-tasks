package module6;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Class providing the main method which reads data from:
 *     http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt
 * and finds the function out of the following that best fits this data:
 *     f(x) = x^2.0
 *     f(x) = x^2.05
 *     f(x) = 1.0x^2 + 10.0x + 0.0
 */
public class DataAnalysis {

	/**
	 * Performs a goodness of fit calculation, provided by the gofCalculator
	 * object, to find the theory the best fits the data and returns that
	 * theory object.
	 * @param data collection of data points to which the function is fit
	 * @param theories list of functions/theories to fit the data to
	 * @param gofCalculator object that provides the method that describes how
	 *        well a theory fits the data
	 * @return the theory object that best fits the data according
	 */
	private static Theory bestTheory(Collection<DataPoint> data,
			Collection<Theory> theories, GoodnessOfFitCalculator gofCalculator) {
		double bestGoodnessOfFit = Double.POSITIVE_INFINITY;
		Theory bestTheory = null;
		for (Theory theory : theories) {
			double gof = gofCalculator.goodnessOfFit(data, theory);
			System.out.println("Function: `" + theory + "` gives a goodness of git value of " + gof);
			if (gof < bestGoodnessOfFit) {
				bestTheory = theory;
				bestGoodnessOfFit = gof;
			}
		}
		return bestTheory;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		// url that contains the data to be fit
		final String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt";

		System.out.println("Loading data points and labeled data points from: ");
		System.out.println("    "+url);
		// Load data from url
		ArrayList<DataPoint> data = TestDataPoints.dataFromURL(url);

		// array of theories to be fitted against the data
		Theory theories[] = {
			new PowerLawTheory(2),
			new PowerLawTheory(2.05),
			new QuadraticTheory(1, 10, 0),
		};

		System.out.println("\nTesting the goodness of fit for the following " +
				"functions using the chi squared methods: ");

		// find which theory object best fits the data using the chi squared method
		// for calculating the goodness of fit
		Theory bestT = bestTheory(data, Arrays.asList(theories), new ChiSquared());
		System.out.println("\nThe function that best fit the data is: " + bestT);
	}

}
