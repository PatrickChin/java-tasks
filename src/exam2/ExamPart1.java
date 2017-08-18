package exam2;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class implementing a main method which reads all recordings from the url RecordingReader.urlbase
 * and prints the the duration, amplitude in dBFS and instrument of each recording. 
 */
public class ExamPart1 {
	
	public static void main(String[] args) {
		ArrayList<Recording> recordings;
		try {
			// read all recordings from url including instruments
			recordings = RecordingReader.getFullRecordingsFromUrlBase(RecordingReader.urlbase);
		} catch (IOException e) {
			// Other parsing errors are caught previously, only parsing the index file is caught here
			System.err.println("There was an error thrown while trying to parse the index file from: " + 
					RecordingReader.urlbase);
			e.printStackTrace();
			return;
		}
		
		for (Recording rec : recordings) {
			System.out.println(rec.getFilename());
			System.out.format("Duration: %.3f s%n", rec.getDuration());	
			System.out.format("Amplitude: %.3f dBFS%n", rec.getSignalAmplitude());
			System.out.format("Instrument: %s%n", rec.getInstrument());
			System.out.println();
		}
	}

}
