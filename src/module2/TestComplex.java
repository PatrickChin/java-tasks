package	module2;

import module2.Complex;

/** Class containing tests for performing operation on complex numbers */
public class TestComplex {

	public static void main(String[] args) {
		Complex c1 = new Complex(2.0, -1.0);
		Complex c2 = new Complex(-1.0, 2.0);

		System.out.println("Initial Complex numbers: ");
		System.out.println("  c1 = "+c1);
		System.out.println("  c2 = "+c2);
		System.out.println();

		System.out.println("Performing operations on complex numbers: ");
		System.out.println("  c1 x c2 = "  + Complex.multiply(c1, c2));
		System.out.println("  c1 / c2 = "  + Complex.divide(c1, c2));
		System.out.println("  c1 x I  = "  + Complex.multiply(c1, Complex.I));
		System.out.println("  c1 / ZERO  = "  + Complex.divide(c1, Complex.ZERO));
		System.out.println("  c1 x c1* = " + Complex.multiply(c1, c1.conjugate()));
		System.out.println("  c2 x c2* = " + Complex.multiply(c2, c2.conjugate()));
	}

}
