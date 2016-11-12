package module5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


/**
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
			ArrayList<DataPoint> urldata = dataFromURL(url);

			Theory f = new Theory(2);
			Theory g = new Theory(4);

			double chi2_f = goodnessOfFit(f, urldata);
			double chi2_g = goodnessOfFit(g, urldata);

			System.out.println("For f(x) = x^2, the chi squared value is: "+chi2_f);
			System.out.println("For f(x) = x^4, the chi squared value is: "+chi2_g);

			System.out.format("f(x) = x^%d is the better fit\n",
					chi2_f < chi2_g ? 2 : 4);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
