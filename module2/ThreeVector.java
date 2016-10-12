package module2;

/**
 *  A 3-element vector represented by double-precision floating point x,y,z coordinates.
 */
public class ThreeVector {

	double x, y, z;

	/**
	 * Constructs and initalises a ThreeVector to (0,0,0)
	 */
	public ThreeVector() {
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * Constructs and initalises a ThreeVector to the same values as v.
	 */
	public ThreeVector(ThreeVector v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}

	/**
	 * Constructs and initalises a ThreeVector to (x,y,z).
	 */
	public ThreeVector(double x, double  y, double  z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Returns the square root of the sum of squares of all the vector
	 * components.
	 */
	public double magnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}

	/**
	 * Returns the element-wise sum of this vector and vector v.
	 */
	public ThreeVector add(ThreeVector v) {
		return new ThreeVector(x+v.x, y+v.y, z+v.z);
	}

	/**
	 * Returns the angle between this and the vector v.
	 */
	public double angle(ThreeVector v) {
		return angle(this, v);
	}

	/**
	 * Returns the scalar product between this and the vector v.
	 */
	public double scalarProduct(ThreeVector v) {
		return scalarProduct(this, v);
	}

	/**
	 * Returns the vector product between this and the vector v.
	 */
	public ThreeVector vectorProduct(ThreeVector v) {
		return vectorProduct(this, v);
	}

	/**
	 * Returns the normalised unit vector in the direction as the vector this.
	 * If this is a null vector another null vector is returned.
	 */
	public ThreeVector unitVector() {
		double mag = magnitude();
		if (mag == 0) {
			return new ThreeVector();
		}
		return new ThreeVector(x/mag, y/mag, z/mag);
	}

	/**
	 * Returns the scalar product between two vectors v1 and v2.
	 */
	public static double scalarProduct(ThreeVector v1, ThreeVector v2) {
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}

	/**
	 * Returns the vector product between two vectors v1 and v2.
	 */
	public static ThreeVector vectorProduct(ThreeVector v1, ThreeVector v2) {
		double x = v2.z*v1.y - v2.y*v1.z;
		double y = v2.x*v1.z - v2.z*v1.x;
		double z = v2.y*v1.x - v2.x*v1.y;
		return new ThreeVector(x, y, z);
	}

	/**
	 * Returns the angle between the vectors v1 and v2.
	 */
	public static double angle(ThreeVector v1, ThreeVector v2) {
		double m1 = v1.magnitude();
		double m2 = v2.magnitude();
		if (m1 == 0.0 || m2 == 0.0) {
			// What would be the best way to deal with this?
		}
		double d = scalarProduct(v1, v2);
		return Math.acos(d / (m1 * m2));
	}

	// public String toString() {
	// 	return String.format("%1$s[%2$.2f, %3$.2f, %4$.2f]",
	// 			this.getClass().getName(),
	// 			this.x, this.y, this.z);
	// }

}

