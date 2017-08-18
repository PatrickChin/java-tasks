package exam2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class representing a recording of a sound possibly played by an instrument.
 */
public class Recording {
	
	// These member variables will be initialised in the constructor
	String filename;
	int freq;
	int nSamples;
	int aMax;
	
	// These member variables will not be initialised in the constructor
	Optional<String> instrument = Optional.empty();
	Optional<Double> duration = Optional.empty();
	Optional<Double> rms = Optional.empty();
	
	// list containing signal amplitude data 
	ArrayList<Integer> data = new ArrayList<Integer>();
	
	public Recording(String filename, int freq,	int nSamples, int aMax) {
		this.filename = filename;
		this.freq = freq;
		this.nSamples = nSamples;
		this.aMax = aMax;
	}
	
	public void addData(Integer data) {
		this.data.add(data);
	}

	public void addAllData(List<Integer> data) {
		this.data.addAll(data);
	}

	///////////////////////////////
	// Getters and setters below //
	///////////////////////////////
	
	public double getDuration() {
		if (!duration.isPresent())
			duration = Optional.of((double) nSamples / freq);
		return duration.get();
	}
	
	public double getRMS() {
		if (!rms.isPresent()) {
			int n = data.size();
			double sum = 0.0;
			for (int a : data) {
				sum += (double) a*a;
			}
			double mean = sum / n;
			rms = Optional.of(Math.sqrt(mean));
		}
		return rms.get();
	}
	
	public double getSignalAmplitude() {
		return 20.0 * Math.log10(getRMS() / aMax);
	}
	
	public String getFilename() {
		return filename;
	}

	public String getInstrument() {
		return instrument.orElse("Unknown");
	}

	public int getFreq() {
		return freq;
	}

	public int getnSamples() {
		return nSamples;
	}

	public int getaMax() {
		return aMax;
	}

	public ArrayList<Integer> getData() {
		return data;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setInstrument(String instrument) {
		this.instrument = Optional.of(instrument);
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public void setnSamples(int nSamples) {
		this.nSamples = nSamples;
	}

	public void setaMax(int aMax) {
		this.aMax = aMax;
	}

	public void setData(ArrayList<Integer> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recording [filename=");
		builder.append(filename);
		builder.append(", instrument=");
		builder.append(instrument);
		builder.append(", freq=");
		builder.append(freq);
		builder.append(", nSamples=");
		builder.append(nSamples);
		builder.append(", aMax=");
		builder.append(aMax);
		builder.append("]");
		return builder.toString();
	}

}
