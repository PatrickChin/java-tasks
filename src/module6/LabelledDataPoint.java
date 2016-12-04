package module6;

import java.util.Scanner;

/**
 * Extension of DataPoint class that also contains a label
 * in the form of a string.
 */
public class LabelledDataPoint extends DataPoint {
	
	String label;

	/** Constructors initialising all members of the object */
	public LabelledDataPoint(double x, double y, double ey, String label) {
		super(x, y, ey);
		this.label = label;
	}

	/**
	 * Returns a new LabelledDataPoint object from a string of three whitespace
	 * delimited doubles, where x is the first, y is the second and ey is the
	 * third.
	 */
	public static LabelledDataPoint fromString(String s) {
		Scanner sc = new Scanner(s);
		double x = sc.nextDouble();
		double y = sc.nextDouble();
		double ey = sc.nextDouble();
		String label = sc.next();
		sc.close();
		return new LabelledDataPoint(x, y, ey, label);
	}

	@Override
	public String toString() {
		return label + ": " + super.toString();
	}
	
}
