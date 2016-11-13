package module5;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.Map;

/**
 * Analyses two urls, one containing a list of mineral samples with a code number
 * and mass for each sample and another containing the code number and location of origin.
 * analyseMaps finds the ssamples with the largest   and smallest mass, and for each, prints
 * out:
 *  - the code number
 *  - the mass
 *  - the location origin
 */
public class Minerals {

	/** HashMaps containint data about the origin of the material */
	private HashMap<Integer,String> locationMap;
	/** HashMaps containint data about the mass of the material */
	private HashMap<Integer,Double> massMap;

	/** Variable for finding the lightest element */
	private Integer minMassKey = 0;
	private Double maxMass = 0.0;
	private String minMassLoc;

	/** Variable for finding the heaviest element */
	private Integer maxMassKey = 0;
	private Double minMass =  Double.POSITIVE_INFINITY;
	private String maxMassLoc;

	/**
	 * Reads from the input URLs, initialising the data hash maps and analysing
	 * the data.
	 */
	public Minerals(String massURL, String locationURL) throws IOException {
		initMassMap(massURL);
		initLocationMap(locationURL);
		analyseMaps();
	}

	/**
	 * Read from URL and parse response into a hash map containing location of
	 * origin data for each mineral's code number.
	 */
	public void initLocationMap(String url) throws IOException {
		// Initialise URL response scanner
		Scanner sc = new Scanner(new URL(url).openStream());
		locationMap = new HashMap<Integer,String>();

		int lineNum = 0;
		while (sc.hasNextLine()) {
			lineNum++;
			Scanner lineScanner = new Scanner(sc.nextLine());

			// Try and parse line and skip if cannot parse
			try {
				String val = lineScanner.next();
				Integer key = lineScanner.nextInt();
				locationMap.put(key, val);
			} catch (InputMismatchException e) {
				System.err.println("Line number " + lineNum + " from the url:\n\t" +
						url + "\ncould not be parsed. Skipping.");
			}
		}
	}

	/**
	 * Read from URL and parse response into as hash map containing mass data
	 * for each mineral's code number.
	 */
	public void initMassMap(String url) throws IOException {
		// Initialise URL response scanner
		Scanner sc = new Scanner(new URL(url).openStream());
		massMap = new HashMap<Integer,Double>();

		int lineNum = 0;
		while (sc.hasNextLine()) {
			lineNum++;
			Scanner lineScanner = new Scanner(sc.nextLine());

			// Try and parse line and skip if cannot parse
			try {
				Integer key = lineScanner.nextInt();
				Double val = lineScanner.nextDouble();
				massMap.put(key, val);
			} catch (InputMismatchException e) {
				System.err.println("Line number " + lineNum + " from the url:\n\t" +
						url + "\ncould not be parsed. Skipping.");
			}
		}

	}


	/**
	 * Itterates through each entry in the hash map of masses and finds
	 * and stores the key, the value and the location of origin for the
	 * lightest and heaviest elements.
	 */
	public void analyseMaps() {
		for (Map.Entry<Integer,Double> entry : massMap.entrySet()) {
			Integer key = entry.getKey();
			Double val = entry.getValue();
			if (val > maxMass) {
				maxMass = val;
				maxMassKey = key;
			}
			if (val < minMass) {
				minMass = val;
				minMassKey = key;
			}
		}
		minMassLoc = locationMap.get(minMassKey);
		maxMassLoc = locationMap.get(maxMassKey);
	}

	/**
	 * Prints the result of analysing the hash maps.
	 */
	public void printResults() {
		System.out.println("Lightest element: ");
		System.out.println("\tCode number: " + minMassKey);
		System.out.println("\tMin: " + minMass);
		System.out.println("\tLocation: " + minMassLoc);

		System.out.println();

		System.out.println("Heaviest element: ");
		System.out.println("\tCode number: " + maxMassKey);
		System.out.println("\tMax: " + maxMass);
		System.out.println("\tLocation: " + maxMassLoc);
	}

	public static void main(String[] args) {
		String massDataURL = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-samples.txt";
		String locationDataURL = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-locations.txt";

		try {
			// Initiallise and analyse the data in the in the URLs
			Minerals mineralAnalysis = new Minerals(massDataURL, locationDataURL);
			mineralAnalysis.printResults();
		} catch (IOException e) {
			// Failed to read from url
			System.err.println(e.getMessage());
		}
	}


}
