package module4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.IllegalStateException;

/**
 * Class used to find all digits in a text file or in the contents of a url.
 * The minimum and maximum values are found as well as the sum of all the
 * values and the number of numbers found. Each number found is written to a
 * file in a user specified directory.
 */
public class NumericalReader {

	/** Writer to write numbers to file */
	private FileWriter fileWriter;
	/** BufferedReader to read and find numbers in */
	private BufferedReader bufferedReader;

	private double minValue, maxValue, sumOfValues;
	private int nValues;

	/** Whether or not analyseStart has been called */
	private boolean started = false;

	/**
	 * Initialise all internal variables except for the BufferedReader.
	 */
	void analysisStart(String outFileStr) throws IOException {

		// set filewriter to overwrite file.
		fileWriter = new FileWriter(outFileStr, false);

		System.out.println("Writing to file: " + outFileStr);

		// Resetting stored values
		minValue = 0.0;
		maxValue = 0.0;
		sumOfValues = 0.0;
		nValues = 0;
		started = true;
	}

	/**
	 * Calls this.analyseData on all lines in bufferedReader
	 */
	void analyseAll() throws IOException {

		if (!started) {
			// Unsure if this if the right exception type to throw here
			throw new IllegalStateException("`this.analysisStart()` has not been " +
					"called before calling `this.analyseAll()`\n" +
					"This is a coding error, please report this bug.");
		}

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			analyseData(line);
		}
	}

	/**
	 * Finds all numbers in the string `line` and prints the number found to
	 * the console and writes the number to the file specifies in
	 * this.analysisStart.
	 *
	 * Lines that start with "x " or "c " are ignored. Note that 'x' or 'c'
	 * must be the first character on that line for it to be ignored.
	 *
	 * Only numbers in the following forms are detected:
	 * #
	 * #.
	 * #.#
	 * .#
	 * where # represents one or more digits.
	 * Numbers can be prepended with a `-` symbol.
	 */
	private void analyseData(String line) throws IOException {

		// Ignore comment line
		if (line.startsWith("x ") || line.startsWith("c ")) {
			return;
		}

		// Scan each word in line
		Scanner sc = new Scanner(line);
		while (sc.hasNext()) {
			String word = sc.next();

			// If not parsable double
			// Another method could be to use `isParsable` from
			if (!word.matches("-?\\d*\\.?\\d+")) {
				continue;
			}

			// Strings that match the above pattern will not throw a
			// NumberFormatException when parsed.
			double d = Double.parseDouble(word);

			System.out.println("Found number: " + d);
			fileWriter.append(Double.toString(d) + "\n");

			if (nValues == 0) {
				// Set min, max and sum values to the first value found.
				minValue = d;
				maxValue = d;
				sumOfValues = d;
			} else {
				minValue = Math.min(minValue, d);
				maxValue = Math.max(maxValue, d);
				sumOfValues += d;
			}
			nValues++;
		}
	}

	/**
	 * Close reader and writer and print out recorded values
	 */
	void analysisEnd() throws IOException {
		bufferedReader.close();
		fileWriter.close();

		System.out.println("Minimum value: " + minValue);
		System.out.println("Maximum value: " + maxValue);
		System.out.println("Average value: " + sumOfValues/nValues);
		System.out.println("Total values read: " + nValues);
	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public double getSumOfValues() {
		return sumOfValues;
	}

	public int getNValues() {
		return nValues;
	}

	// public void setBufferedReaderFromURL(String urlName) throws IOException {
	public BufferedReader brFromURL(String urlName) throws IOException {
		URL url = new URL(urlName);
		InputStreamReader isr = new InputStreamReader(url.openStream());
		bufferedReader = new BufferedReader(isr);
		return bufferedReader;
	}

	/**
	 * Prints msg and reads and returns a single line of trxt from the console.
	 */
	public static String getStringFromKeyboard(String msg) {
		System.out.print(msg);
		Scanner sc = new Scanner(System.in);
		String  s = sc.nextLine();
		sc.close();
		return s;
	}

	/**
	 * Prompts user to enter a line of text which is read and returned.
	 */
	public static String getStringFromKeyboard() {
		return getStringFromKeyboard("Type something: ");
	}

	public static void main(String[] args) {

		final String dataUrls[] = {
			"http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data1.txt",
			"http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data2.txt"
		};
		final String outFileNames[] = {"numbers1.txt", "numbers2.txt"};

		String outDirStr;

		while (true) {
			// Get directory to write output files to
			outDirStr = getStringFromKeyboard("Enter directory to save data " +
					"files to (leave empty for home directory):\n\t");

			// If no user input directory, write to home directory.
			if (outDirStr.isEmpty()) {
				outDirStr = System.getProperty("user.home");
				break;
			}

			File f = new File(outDirStr);

			if (f.exists() && f.isDirectory() && f.canWrite()) {
				break;
			}

			System.out.println("Error: \"" + outDirStr + "\" is not a valid " +
					"directory or you do not have permission to write to this directory.");
		}

		NumericalReader nr = new NumericalReader();

		try {
			// Find all numbers in first data file
			nr.brFromURL(dataUrls[0]);
			nr.analysisStart(outDirStr + File.separator + outFileNames[0]);
			nr.analyseAll();
			nr.analysisEnd();

			System.out.println();

			// Find all numbers in second data file
			nr.brFromURL(dataUrls[1]);
			nr.analysisStart(outDirStr + File.separator + outFileNames[1]);
			nr.analyseAll();
			nr.analysisEnd();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

