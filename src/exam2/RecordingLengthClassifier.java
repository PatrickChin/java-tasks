package exam2;

public class RecordingLengthClassifier implements SoundClassifier {

	@Override
	public String classify(Recording rec) {
		if (rec.getDuration() > 1.0) {
			return "long";
		} else {
			return "short";
		}
	}

}
