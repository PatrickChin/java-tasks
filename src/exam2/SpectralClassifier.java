package exam2;

import java.util.List;

/**
 * Class implementing the SoundClassifier interface which calculates whether a
 * recording has a "low", "medium", or "high" spectral density.
 */
public class SpectralClassifier implements SoundClassifier {

	/** Calculates the spectral density of a list of samples of duration t at frequency f */
	private double spectralDensity(List<Integer> samples, double t, double f) {
		int bigN = samples.size();
		double z = 2 * Math.PI * f * t / bigN;
		double sumCos = 0;
		double sumSin = 0;
		for (int n = 0; n < bigN; ++n) {
			sumCos += samples.get(n)* Math.cos(z*n);
			sumSin += samples.get(n)* Math.sin(z*n);
		}
		double norm = t / (bigN*bigN);
		return norm * (sumCos*sumCos + sumSin*sumSin);
	}

	@Override
	/**
	 * Specifies whether a recording has a "low", "medium", or "high" spectral density.
	 * This is determined by calculating the spectral densities at the following frequencies
	 * and returns the related string depending on which spectral density is greatest.
	 *  100 Hz: "low"
	 *  400 Hz: "medium"
	 *  1 kHz : "high" 
	 */
	public String classify(Recording rec) {
		List<Integer> samples = rec.getData();
		double t = rec.getDuration();
		
		double dens100 = spectralDensity(samples, t, 100);
		double dens400 = spectralDensity(samples, t, 400);
		double dens1e3 = spectralDensity(samples, t, 1e3);
		
		if (dens100 > dens400 && dens100 > dens1e3) { // if dens100 is greatest
			return "low";
		} else if (dens400 > dens1e3) { // if dens400 is greatest
			return "medium";
		} else { // dens1e3 is greatest
			return "high";
		}
	}

}
