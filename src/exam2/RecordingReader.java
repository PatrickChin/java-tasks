package exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class implementing static methods to read online data of sound recordings into
 * ArrayLists of Recordings.
 */
public class RecordingReader {
	
	/** 
	 * This is the url of the directory where the index.txt file is placed. This will act as
	 * the base directory.
	 */
	public static String urlbase = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2016-17";

	/**
	 * Get a map of all the data files along with what instruments each file is a recording of.
	 */
	public static Map<String,String> getInstrumentMapFromURL(String url) 
			throws IOException {
		
		// Initialise map of instrument (value) and filenames (key)
		Map<String,String> instrumentMap = new  HashMap<String,String>();

		// Use a try-with-resources block to read from url and close streams
		try (InputStream stream = new URL(url).openStream();
			 InputStreamReader ir = new InputStreamReader(stream);
			 BufferedReader br = new BufferedReader(ir);
		) {
			String line;
			while ((line = br.readLine()) != null) {

				if (line.isEmpty()) {
					continue; // ignore empty lines (i.e. the last line which is empty)
				}
				
				Scanner sc = new Scanner(line);
				String filename = sc.next(); // key
				String instrument = sc.next(); // value
				instrumentMap.put(filename, instrument);
				sc.close();
			}
		}
		return instrumentMap;
	}
	
	/**
	 * Return a Recording object parsed from the file with the same filename under the url urlbase.
	 */
	public static Recording getRecordingFromURL(String urlbase, String filename) 
			throws IOException {
		
		// Concatenate the urlbase and filename to get the full url
		String url = urlbase;
		if (url.charAt(url.length()-1) != '/') {
			url += '/';
		}
		url += filename;
		
		// Use a try-with-resources block to read from url and close streams
		try (InputStream stream = new URL(url).openStream();
			 InputStreamReader ir = new InputStreamReader(stream);
			 BufferedReader br = new BufferedReader(ir);
		) {
			Recording rec;
			
			// For the first line read in the header data
			String line = br.readLine();
			try (Scanner sc = new Scanner(line)) {
				int freq = sc.nextInt();
				int nSamples = sc.nextInt();
				int aMax = sc.nextInt();
				rec = new Recording(filename, freq, nSamples, aMax);
			}
			
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue; // ignore empty lines (i.e. the last line which is empty)
				}
				int amplitude = Integer.parseInt(line);
				rec.addData(amplitude);
			}
			
			return rec;
		}
	}

	/**
	 * Return a list of Recording objects (with the instrument variable initialised)
	 * parsed from all the files specified in index.txt under the urlbase.
	 */
	public static ArrayList<Recording> getFullRecordingsFromUrlBase(String urlbase) 
			throws IOException {

		String indexurl = urlbase + "/index.txt";
		Map<String,String> instrumentMap = getInstrumentMapFromURL(indexurl);
		ArrayList<Recording> recordings = new ArrayList<>();
		
		// For all data files found in index.txt
		for (Map.Entry<String,String> entry : instrumentMap.entrySet()) {
			String filename = entry.getKey();
			String instrument = entry.getValue();

			try {
				Recording rec = getRecordingFromURL(urlbase, filename);
				rec.setInstrument(instrument);
				recordings.add(rec);
			} catch (IOException e) {
				System.out.format("There was an error parsing the file at %s%n", filename);
				System.out.println("Skipping ... ");
				e.printStackTrace();
			}
		}
		
		return recordings;
	}

}
