package module6;

/**
 * Class that represents the mathematical equation:
 *     f(x) = x^n
 * where n is a function parameter.
 * This class implements the Theory interface meaning
 * it provides the function y(x) that returns the value
 * of y at the point x.
 */
public class PowerLawTheory implements Theory {

	/** Parameter for the function */
	private double n;
	
	/** Constructor initialising the function parameter n */
	public PowerLawTheory(double n) {
		this.n = n;
	}

	@Override
	public double y(double x) {
		return Math.pow(x,n);
	}

	@Override
	public String toString() {
		return "f(x) = x^" + n;
	}

}
