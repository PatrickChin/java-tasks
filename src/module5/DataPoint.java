package module5;

import java.util.Scanner;

/**
 * A simple class representing a measurement y along with it's error ey that
 * depends on variable x. These are stored privately and can be accessed via get
 * methods.
 */
public class DataPoint {

	private double x;
	private double y;
	private double ey;

	public DataPoint(double x, double y, double ey) { 
		this.x = x;
		this.y = y;
		this.ey = ey;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getEy() {
		return ey;
	}

	/**
	 * Returns a new DataPoint object from a string of three whitespace
	 * delimited doubles, where x is the first, y is the second and ey is the
	 * third.
	 */
	public static DataPoint fromString(String s) {
		Scanner sc = new Scanner(s);
		double x = sc.nextDouble();
		double y = sc.nextDouble();
		double ey = sc.nextDouble();
		sc.close();
		return new DataPoint(x, y, ey);
	}

}
