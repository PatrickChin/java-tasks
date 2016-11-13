package module5;

import java.net.URL;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Analyses a url containinst a list of experimental results where each line
 * contains three values in the order: x, y and ey, where x is a parameter set
 * by the experimenter, y is a measurement of a dependent variable, and ey is
 * the measurement error on y. Provides a function, goodnessOfFit, that
 * calculated the chi squared value from fitting a theoretical function to the
 * given data.
 */
public class DataAnalysis {

	/**
	 * Reads the response from the input url, and
	 * parses each line into a new DataPoint object, returning
	 * an ArrayList of each parsed DataPoint
	 */
	public static ArrayList<DataPoint> dataFromURL(String url) 
			throws IOException {

		// Initialise URL response scanner
		Scanner sc = new Scanner(new URL(url).openStream());

		// Initialise empty ArrayList of DataPoints
		ArrayList<DataPoint> arr = new ArrayList<DataPoint>();

		while (sc.hasNextLine()) {
			String line = sc.nextLine();

			// Try and parse line to a DataPoint object
			// Skip if cannot parse
			try {
				DataPoint p = DataPoint.fromString(line);
				arr.add(p);
			} catch (InputMismatchException e) {
				System.err.println("A line from the url could not be parsed. Skipping.");
			}
		}
		return arr;
	}

	/**
	 * Returns the chi squared value representing how well the Theory function
	 * fits the given data in arr.
	 */
	public static double goodnessOfFit(Theory f, ArrayList<DataPoint> arr) {
		int size = arr.size();
		double chi2 = 0;

		// sum the chi squared contributions of each data point
		for (int i = 0; i < size; i++) {
			DataPoint p = arr.get(i);
			double x = p.getX();
			double y_theory = f.y(x);
			double y_data = p.getY();
			double y_err = p.getEy();

			double y_diff = y_data - y_theory;

			// Add another term to chi2
			chi2 += y_diff*y_diff / (y_err * y_err);
		}
		return chi2;
	}

	public static void main(String[] args) {
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-xy.txt";

		try {
			System.out.println("Retrieving data from " + url);
			ArrayList<DataPoint> urldata = dataFromURL(url);

			// Initalise functions to try and fit data to
			Theory f = new Theory(2);
			Theory g = new Theory(4);

			// Calculate chi squared values for each fit function
			System.out.println("Calculating goodness of fits...");
			double chi2_f = goodnessOfFit(f, urldata);
			double chi2_g = goodnessOfFit(g, urldata);

			System.out.println("For f(x) = x^2, the chi squared value is: "+chi2_f);
			System.out.println("For f(x) = x^4, the chi squared value is: "+chi2_g);

			// Print out which is the best fit i.e. the function with the lowe chi squared value
			System.out.format("f(x) = x^%d is the better fit\n", chi2_f < chi2_g ? 2 : 4);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
