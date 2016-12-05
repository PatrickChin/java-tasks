package module6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads DataPoint and LabelledDataPoint objects from the url:
 *     http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt
 * and prints out the parsed data points.
 */
public class TestDataPoints {
	
	/**
	 * Reads the response from the url line by line, parsing
	 * each line into a DataPoint object or LabelledDataPoint object
	 * which is added to an ArrayList which is then returned.
	 */
	public static ArrayList<DataPoint> dataFromURL(String url)
		throws MalformedURLException, IOException {

		// ArrayList to be returned
		ArrayList<DataPoint> ret = new ArrayList<>();

		// Try resource block to instantiate a buffered reader from the url
		try (BufferedReader br = 
				new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {

			// Read all lines in buffered reader
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				Scanner sc = new Scanner(line);
				double x = sc.nextDouble();
				double y = sc.nextDouble();
				double ey = sc.nextDouble();

				// Instantiate a labelled data point if label exists
				DataPoint p = sc.hasNext() ? 
						new LabelledDataPoint(x, y, ey, sc.next()) :
						new DataPoint(x, y, ey);
				sc.close();
				ret.add(p);
			}
		}
		return ret;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt";
		// Read all data points from url
		ArrayList<DataPoint> data = dataFromURL(url);

		System.out.println("Parsed DataPoint objects from the url:");
		System.out.println("    " + url);
		System.out.println();

		// Print all data points parsed
		System.out.println("DataPoints parsed were as follows: ");
		for (DataPoint dp : data) {
			System.out.println(dp);
		}
	}

}
