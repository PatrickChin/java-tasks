package module9;

/**
 *  A 3-element vector represented by double-precision floating point x,y,z
 *  coordinates.
 */
public class ThreeVector {

	public final static ThreeVector ZERO = new ThreeVector(0, 0, 0);

	private final double x, y, z;

	/** Constructs and initialises a ThreeVector to the same values as v.  */
	public ThreeVector(ThreeVector v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}

	/** Constructs and initialises a ThreeVector to (x,y,z).  */
	public ThreeVector(double x, double  y, double  z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/** Returns the sum of squares of all the vector components.  */
	public double magnitudeSq() {
		return x*x + y*y + z*z;
	}

	/** Returns the square root of the sum of the square of the vector components. */
	public double magnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}

	/** Returns the ThreeVector with all components negated */
	public ThreeVector negate() {
		return new ThreeVector(-x, -y, -z);
	}

	/** Scales all components of this vector by s */
	public ThreeVector multiply(double s) {
		return new ThreeVector(x*s, y*s, z*s);
	}

	/** Scales all components of this vector by 1/s */
	public ThreeVector divide(double s) {
		return new ThreeVector(x/s, y/s, z/s);
	}

	/** Returns the element-wise sum of this vector and vector v.  */
	public ThreeVector add(ThreeVector v) {
		return new ThreeVector(x+v.x, y+v.y, z+v.z);
	}

	/** Returns the scalar product between this and the vector v.  */
	public double dot(ThreeVector v) {
		return dot(this, v);
	}

	/** Returns the vector product between this and the vector v.  */
	public ThreeVector cross(ThreeVector v) {
		return cross(this, v);
	}

	/** Returns the angle between this and the vector v.  */
	public double angleTo(ThreeVector v) {
		return angleBetween(this, v);
	}

	/**
	 * Returns the normalised unit vector in the direction as the vector this.
	 * Throws if called from a null vector.
	 */
	public ThreeVector normalise() {
		double mag = magnitude();
		if (mag == 0.0) {
			throw new ArithmeticException("Cannot find the unit vector of a zero vector");
		}
		return new ThreeVector(x/mag, y/mag, z/mag);
	}

	public String toString2() {
		return String.format("[%1$.2f, %2$.2f, %3$.2f]",
				this.x, this.y, this.z);
	}


	// Static methods

	/** Returns the elementwise sum of the vectors v1 and v2. */
	public static ThreeVector add(ThreeVector v1, ThreeVector v2) {
		return new ThreeVector(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z);
	}

	/** Returns the elementwise subtraction of the vectors v2 from v1. */
	public static ThreeVector subtract(ThreeVector v1, ThreeVector v2) {
		return new ThreeVector(v1.x-v2.x, v1.y-v2.y, v1.z-v2.z);
	}

	/** Returns the scalar product between two vectors v1 and v2. */
	public static double dot(ThreeVector v1, ThreeVector v2) {
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}

	/** Returns the vector product between two vectors v1 and v2. */
	public static ThreeVector cross(ThreeVector v1, ThreeVector v2) {
		double x = v2.z*v1.y - v2.y*v1.z;
		double y = v2.x*v1.z - v2.z*v1.x;
		double z = v2.y*v1.x - v2.x*v1.y;
		return new ThreeVector(x, y, z);
	}

	/**
	 * Returns the angle between the vectors v1 and v2 in the range 0.0 through
	 * pi (as defined by the Math.acos function).  Throws if v1 or v2 is a null
	 * vector.
	 */
	public static double angleBetween(ThreeVector v1, ThreeVector v2) {
		double m1 = v1.magnitude();
		double m2 = v2.magnitude();
		if (m1 == 0.0 || m2 == 0.0) {
			throw new ArithmeticException("Cannot find the angle between one or more zero vectors");
		}
		double d = dot(v1, v2);
		return Math.acos(d / (m1 * m2));
	}

}

