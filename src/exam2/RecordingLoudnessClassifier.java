package exam2;

public class RecordingLoudnessClassifier implements SoundClassifier {

	@Override
	public String classify(Recording rec) {
		if (rec.getSignalAmplitude() > -20.0) {
			return "loud";
		} else {
			return "quiet";
		}
	}

}
