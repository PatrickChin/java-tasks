package module6;

/**
 * Class that represents the mathematical equation:
 *     f(x) = ax^2 + bx + c
 * where a, b and c are function parameters defined on construction.  This
 * class implements the Theory interface meaning it provides the function y(x)
 * that returns the value of y at the point x.
 */
public class QuadraticTheory implements Theory {

	/** Parameters for the function */
	private final double a, b, c;
	
	/** Constructor initialising all the function parameters */
	public QuadraticTheory(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public double y(double x) {
		return a*x*x + b*x + c;
	}

	@Override
	public String toString() {
		return "f(x) = " + a+"x^2 + " + b+"x + " + c;
	}

}
