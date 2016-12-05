package module6;

import java.util.Collection;

/**
 * Interface that provides a function which determines how well a Theory object
 * fits a collection of data points
 */
public interface GoodnessOfFitCalculator {

	/**
	 * Abstract function that returns a double value that represents how well
	 * the function provided by `theory` fits the collection of DataPoint
	 * objects
	 */
	public double goodnessOfFit(Collection<DataPoint> data, Theory theory);

}
