package module4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// import org.apache.commons.lang3.math;

public class NumericalReader {

	private File dataFile;
	private double minValue, maxValue, nValues, sumOfValues;

	public NumericalReader() {
		
	}

	public BufferedReader brFromURL(String urlName) throws IOException {
		URL url = new URL(urlName);
		InputStreamReader isr = new InputStreamReader(url.openStream());
		return new BufferedReader(isr);
	}
	
	void analysisStart(String dataFile) {
		this.dataFile = new File(dataFile);
	}

	void analyseData(String line) {
		if (dataFile == null)
			return;
	}

	void analysisEnd() {
		System.out.println("Minimum value: " + minValue);
		System.out.println("Maximum value: " + maxValue);
		System.out.println("Average value: " + sumOfValues/nValues);
	}


	public static String getStringFromKeyboard() {
		System.out.print("Type something: ");
		String s = System.console().readLine();
		return s;
	}

}
