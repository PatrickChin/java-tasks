package module3;

/**
 * A class representing a complex number with real and imaginary parts stored
 * as doubles.
 */
public class Complex {

	/** Real and imaginary parts to the complex number */
	public double real;
	public double imag;

	/** Constants for the values: 1, 0, i.  */
	public final static Complex ONE  = new Complex(1.0, 0.0);
	public final static Complex ZERO = new Complex(0.0, 0.0);
	public final static Complex I    = new Complex(0.0, 1.0);

	/** Default constructor, creates an object equal to ZERO.  */
	public Complex() {
		real = 0.0;
		imag = 0.0;
	}

	/**
	 * Copy constructor, creates an object equal to z, with equal real and
	 * imaginary parts.
	 */
	public Complex(Complex z) { 
		real = z.real;
		imag = z.imag;
	}

	/** Creates a Complex object with real and imaginary parts.  */
	public Complex(double real, double imag) { 
		this.real = real;
		this.imag = imag;
	}

	/** Returns the modulus of the complex number.  */
	public double modulus() {
		return Math.sqrt(real*real + imag*imag);
	}

	/** Returns the argument of the complex number */
	public double angle() {
		return Math.atan2(imag, real);
	}

	/**
	 * Returns the complex conjugate of this number by reversing the sign of 
	 * the imaginary part.
	 */
	public Complex conjugate() {
		return new Complex(real, -imag);
	}

	/**
	 * Returns the complex number with the same phase as this and a modulus of
	 * 1.0. Throws exception if magnitude is zero.
	 */
	public Complex normalised() {
		double mag = modulus();
		if (mag == 0.0) {
			throw new ArithmeticException("Cannot normalise complex number (0 + 0i)");
		}
		return new Complex(real/mag, imag/mag);
	}

	/**
	 * Returns true if and only if the real and imaginary parts of this and c
	 * are equal.
	 */
	public boolean equals(Complex c) {
		return real == c.real && imag == c.imag;
	}

	public String toString() {
		return String.format("(%1$.2f + %2$.2fi)", this.real, this.imag);
	}

	/** Returns a Complex number with a magnitude of mag and a phase of ang */
	public static Complex setFromModulusAngle(double mag, double ang) {
		return new Complex(mag*Math.cos(ang), mag*Math.sin(ang));
	}

	/** Computes and returns the sum two complex numbers.  */
	public static Complex add(Complex z1, Complex z2) {
		return new Complex(z1.real + z2.real, z1.imag + z2.imag);
	}

	/** Returns the result of subtracting z2 from z1.  */
	public static Complex subtract(Complex z1, Complex z2) {
		return new Complex(z1.real - z2.real, z1.imag - z2.imag);
	}

	/** Multiplies its arguments.  */
	public static Complex multiply(Complex z1, Complex z2) {
		double real = z1.real*z2.real - z1.imag*z2.imag;
		double imag = z1.real*z2.imag + z1.imag*z2.real;
		return new Complex(real, imag);
	}
	
	/**
	 * Returns the result of dividing z1 by z2.
	 * Multiplies the numerator and denominator by the complex conjugate of
	 * then divides the numerator by the denominator.
	 * Throws if z2 = 0.
	 */
	public static Complex divide(Complex z1, Complex z2) {
		Complex conj = z2.conjugate();
		Complex numer = multiply(z1, conj);
		// Multiplying a complex number with it's complex conjugate will result
		// in a real number so we can ignore the imag part.
		double denom = multiply(z2, conj).real;
		if (denom == 0.0) {
			throw new ArithmeticException("Cannot divide by (0 + 0i)");
		}
		numer.real /= denom;
		numer.imag /= denom;
		return numer;
	}

	/**
	 * Alternative method for dividing z1 by z2.
	 * Method described below
	 *
	 * For z1 = a + b i,
	 *     z2 = c + d i.
	 *
	 * It can be shown that:
	 *   z1      (a.c + b.d) + i(b.c - a.d)
	 *  ----  =  --------------------------
	 *   z2              c^2 + d^2
	 *
	 * Throws if z2 = 0.
	 */
	public static Complex divide2(Complex z1, Complex z2) {
		double denom = z2.real*z2.real + z2.imag*z2.imag;
		if (denom == 0.0) {
			throw new ArithmeticException("Cannot divide by (0 + 0i)");
		}
		double real  = z1.real*z2.real + z1.imag*z2.imag;
		double imag  = z1.imag*z2.real + z1.real*z2.imag;
		return new Complex(real/denom, imag/denom);
	}

}
