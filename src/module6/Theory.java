package module6;

/**
 * Interface providing the method y that takes a double returns a double.
 * This represents any one dimensional function.
 */
@FunctionalInterface
public interface Theory {

	/** @return the result of y(x) */
	public double y(double x);

}
