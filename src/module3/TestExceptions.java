package module3;

/** Class testing the exceptions thrown by ThreeVector, Complex and FallingParticle. */
public class TestExceptions {

	public static void main(String[] args) {

		System.out.println("Test:  dividing by complex zero: ");
		try {
			Complex.divide(new Complex(1,1), Complex.ZERO);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		System.out.println("Test:  finding the normalised complex number of zero: ");
		try {
			Complex.ZERO.normalised();
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		System.out.println("Test:  finding the unit vector of a null vector: ");
		try {
			new ThreeVector(0.0, 0.0, 0.0).unitVector();
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		System.out.println("Test:  finding the angle between a vector and null vector: ");
		try {
			ThreeVector.angle(new ThreeVector(0.0, 0.0, 0.0),
					          new ThreeVector(1.0, 1.0, 1.0));
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		System.out.println("Test:  Initialising the mass of a particle to a negative number: ");
		try {
			new FallingParticle(-1.0, 1.0);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		System.out.println("Test:  Setting the drop height to a negative number. ");
		try {
			new FallingParticle(1.0, 1.0).setZ(-1.0);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

	}

}
