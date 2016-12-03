package module6;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class DataAnalysis {

	private static Theory bestTheory(Collection<DataPoint> data,
			Collection<Theory> theories, GoodnessOfFitCalculator gofCalculator) {
		boolean first = true;
		double bestGoodnessOfFit = 0.0;
		Theory bestTheory = null;
		for (Theory theory : theories) {
			double gof = gofCalculator.goodnessOfFit(data, theory);
			if (first) {
				bestTheory = theory;
				bestGoodnessOfFit = gof;
				first = false;
			} else if (gof < bestGoodnessOfFit) {
				bestTheory = theory;
				bestGoodnessOfFit = gof;
			}
		}
		return bestTheory;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt";
		ArrayList<DataPoint> data = TestDataPoints.dataFromURL(url);
		Theory theories[] = {
			new PowerLawTheory(2),
			new PowerLawTheory(2.05),
			new QuadraticTheory(1, 10, 0)
		};

		Theory best = bestTheory(data, Arrays.asList(theories), new ChiSquared());

		System.out.println(best);

	}

}
