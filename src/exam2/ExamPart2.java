package exam2;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class implementing a main method which reads all recordings from the url RecordingReader.urlbase
 * and prints the classification of all the recordings according to the RecordingLengthClassifier
 * and RecordingLoudnessClassifier classes. 
 */
public class ExamPart2 {

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
		
		// Testing RecordingLengthClassifier on all recordings in urlbase
		SoundClassifier lengthClassifier = new RecordingLengthClassifier();
		for (Recording rec : recordings) {
			String recClass = lengthClassifier.classify(rec);
			System.out.format("The recording in %s can be classified as: %s%n", rec.getFilename(), recClass);
		}
		
		System.out.println();
		
		// Testing RecordingLoudnessClassifier on all recordings in urlbase
		SoundClassifier loudnessClassifier = new RecordingLoudnessClassifier();
		for (Recording rec : recordings) {
			String recClass = loudnessClassifier.classify(rec);
			System.out.format("The recording in %s can be classified as: %s%n", rec.getFilename(), recClass);
		}

	}

}
