package module8;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Class implementing callable that provides a call function that returns an
 * estimate for the value of pi. Pi is estimated using monte carlo sampling,
 * by placing a random point in a square and finding the ratio of points 
 * within a circle in the square and the total number of points.
 */
public class MonteCarloPiCalculatorTask implements Callable<Double> {

	/** Number of random points to generate */
	private final long n_points;

	public MonteCarloPiCalculatorTask(long nPoints) {
		this.n_points = nPoints;
	}

	@Override
	public Double call() {
		Random rand = new Random();
		long n_in  = 0;
		for (long iPoint = 0; iPoint < n_points; ++iPoint) {
			double x = rand.nextDouble();
			double y = rand.nextDouble();
			double r2 = x*x + y*y;
			if (r2 < 1.0) ++n_in; // check if random point is in circle
		}
		return 4.0 * n_in / n_points;
	}

}
