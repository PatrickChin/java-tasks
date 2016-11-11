package module5;

public class TestSquareMatrix {

	public static void main(String[] args) {
		
		// Creating square matrices for manipulation
		SquareMatrix a = new SquareMatrix(new double[][]
										  {{2, 0, -1},
										   {0, 2,  0},
										   {3, 0,  1}});

		SquareMatrix b = new SquareMatrix(new double[][]
										  {{-1, 0, 1},
										   { 0, 1, 0},
										   {-3, 0, 1}});

		SquareMatrix c = new SquareMatrix(new double[][]
										  {{2, 3},
										   {3, 4}});

		SquareMatrix d = new SquareMatrix(new double[][]
										  {{-4,  3},
										   { 3, -2}});

		SquareMatrix i = SquareMatrix.unitMatrix(2);

		// Printing initial matricies
		System.out.println("Initial matricies: ");
		System.out.println("A = ");
		System.out.println(a);
		System.out.println();
		System.out.println("B = ");
		System.out.println(b);
		System.out.println();
		System.out.println("C = ");
		System.out.println(c);
		System.out.println();
		System.out.println("D = ");
		System.out.println(d);
		System.out.println();
		System.out.println("I = ");
		System.out.println(i);
		System.out.println();

		// Testing matrix addition
		System.out.println("Testing matrix addition on A and B: ");
		System.out.println("A + B = ");
		System.out.println(a.add(b));
		System.out.println();

		// Testing matrix subtract
		System.out.println("Testing matrix subtraction on A and B: ");
		System.out.println("A - B = ");
		System.out.println(a.subtract(b));
		System.out.println();

		{
			// Calculating the commutator of A and B, [A,B]
			// This tests matrix multiplication function.
			System.out.println("Test: calculating the commutator of A and B: ");

			SquareMatrix ab = a.multiply(b);
			System.out.println("AB = ");
			System.out.println(ab);
			System.out.println();

			SquareMatrix ba = b.multiply(a);
			System.out.println("BA = ");
			System.out.println(ba);
			System.out.println();

			SquareMatrix ab_ba = ab.subtract(ba);
			System.out.println("The commutator of A and B:");
			System.out.println("[A,B] = (AB-BA) = ");
			System.out.println(ab_ba);
			System.out.println();
		}


		{
			// Testing matrix multiplication
			System.out.println("Testing matrix multiplication on C and D: ");

			SquareMatrix cd = c.multiply(d);
			System.out.println("CD = ");
			System.out.println(cd);
			System.out.println();

			System.out.println("Checking if CD is equal to I:");
			System.out.println("(CD == I) = " + cd.equals(i));
		}

	}
}

