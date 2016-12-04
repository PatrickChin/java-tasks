package module6;

import java.util.Collection;

/**
 * Interface that provides a function which determines how well
 * a Theory object fits a collection of data points
 */
public interface GoodnessOfFitCalculator {
	
	public double goodnessOfFit(Collection<DataPoint> data, Theory theory);

}
