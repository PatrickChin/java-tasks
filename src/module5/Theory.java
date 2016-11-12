package module5;

/**
 * A simple class representing the function:
 *		f(x) = x^n
 * where the function Theory.y(x) returns the value of f(x) at x.
 */
public class Theory {

	double n;

	public Theory(double n) {
		this.n = n;
	}

	/**
	 * returns f(x)
	 */
	public double y(double x) {
		return Math.pow(x, this.n);
	}

}
