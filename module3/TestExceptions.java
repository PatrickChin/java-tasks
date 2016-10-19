package module3;

public class TestExceptions {

	public static void main(String[] args) {
		// TODO comments

		try {
			Complex.divide(new Complex(1,1), Complex.ZERO);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		try {
			Complex.ZERO.normalised();
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		try {
			new ThreeVector(0.0, 0.0, 0.0).unitVector();
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		try {
			ThreeVector.angle(new ThreeVector(0.0, 0.0, 0.0),
					          new ThreeVector(1.0, 1.0, 1.0));
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		try {
			new FallingParticle(-1.0, 1.0);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

		try {
			new FallingParticle(1.0, 1.0).setZ(-1.0);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.err.println();
		}

	}

}
