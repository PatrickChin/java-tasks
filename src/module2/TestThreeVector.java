package module2;

import module2.ThreeVector;

/** Class containing tests for performing operation on ThreeVectors */
public class TestThreeVector {

	public static void main(String[] args) {
		ThreeVector v1 = new ThreeVector(5.0, 2.0, 3.0);
		ThreeVector v2 = new ThreeVector(4.0, 5.0, 1.0);
		ThreeVector v3 = new ThreeVector(0.0, 0.0, 0.0);

		System.out.println("Initial ThreeVectors: ");
		System.out.println("  v1: "+v1.toString2());
		System.out.println("  v2: "+v2.toString2());
		System.out.println("  v3: "+v3.toString2());
		System.out.println();

		System.out.println("Scalar products (static): ");
		System.out.println("  v1.v2: "+ThreeVector.scalarProduct(v1, v2));
		System.out.println("  v1.v3: "+ThreeVector.scalarProduct(v1, v3));
		System.out.println();

		System.out.println("Scalar products (non-static): ");
		System.out.println("  v1.v2: "+v1.scalarProduct(v2));
		System.out.println("  v1.v3: "+v1.scalarProduct(v3));
		System.out.println();

		System.out.println("Angle between vectors (static): ");
		System.out.println("  v1.v2: "+ThreeVector.angle(v1, v2));
		System.out.println("  v1.v3: "+ThreeVector.angle(v1, v3));
		System.out.println();

		System.out.println("Angle between vectors (non-static): ");
		System.out.println("  v1.v2: "+v1.angle(v2));
		System.out.println("  v1.v3: "+v1.angle(v3));
		System.out.println();

		System.out.print("Printing a ThreeVector that does not have a toString method defined prints: ");
		System.out.println(v1);
		System.out.println();
		System.out.println("Without a toString method, printing an object will "+
				"display the class name of the object and it's hash code.");
	}
}
