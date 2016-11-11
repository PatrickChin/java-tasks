package module5;

import java.util.Arrays;

/**
 * Class representing a matrix of dimensions n by n, which provides functions 
 * including add, subtract and multiply.
 */
public class SquareMatrix {

	private int size;
	private double[][] data;

	/**
	 * Initalise SquareMatrix without initalising it's members.
	 */
	private SquareMatrix() { }

	/**
	 * Initalize SquareMatrix using a deep copy of elems.
	 */
	public SquareMatrix(double[][] elems) {
		this.fill(elems);
	}

	/**
	 * Shallow copy data to this.data.
	 * This may expose this.data
	 */
	public void assign(double[][] elems) {
		this.size = calcSize(elems);
		this.data = elems;
	}

	/**
	 * Deep copies the argument data to this.data
	 */
	public void fill(double[][] elems) {
		this.size = calcSize(elems);
		// Initalise zero matrix
		this.data = new double[size][size];
		// Copy each element
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.data[i][j] = elems[i][j];
			}
		}
	}

	/**
	 * Returns the element M_ij where i is the row number and j is the column
	 * number.
	 */
	public double get(int i, int j) {
		return this.data[i][j];
	}

	/**
	 * Returns the length of an array of an array.
	 * Throws if the length of data is less than one or if the length of data
	 * is not equal to the length of the first element of data.
	 */
	private static int calcSize(double[][] data) {
		if (data.length < 1) {
			throw new IllegalArgumentException(
				"Arguments 'elems' must have a length greater than zero."
			);
		}
		if (data.length != data[0].length) {
			throw new IllegalArgumentException(
				"Argument 'elems' must have equal 'width' and 'height'."
			);
		}
		return data.length;
	}

	/**
	 * Create the identity matrix with dimensions size x size.
	 * Throws if size < 0.
	 */
	public static SquareMatrix unitMatrix(int size) {
		if (size < 0) {
			throw new IllegalArgumentException("Cannot have a matrix size less than zero.");
		}
		
		// Each element of this array is initalized to zero
		// as stated by the language spec
		double[][] mat = new double[size][size];
		for (int i = 0; i < size; i++) {
			mat[i][i] = 1;
		}

		return new SquareMatrix(mat);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(data);
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SquareMatrix other = (SquareMatrix) obj;
		if (size != other.size)
			return false;
		if (!Arrays.deepEquals(data, other.data))
			return false;
		return true;
	}

	/**
	 * Returns the resultant matrix of an elementwise addittion between SquareMatrix this and SquareMatrix m
	 */
	public SquareMatrix add(SquareMatrix m) {
		return add(this, m);
	}

	/**
	 * Returns the resultant matrix of an elementwise addittion between SquareMatrix m1 and SquareMatrix m2
	 */
	public static SquareMatrix add(SquareMatrix m1, SquareMatrix m2) {
		if (m1.size != m2.size) {
			throw new IllegalArgumentException("Size of matrixies m1 and m2 must be equal.");
		}

		int size = m1.size;
		// Zero initalise array
		double[][] data = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				data[i][j] = m1.data[i][j] + m2.data[i][j];
			}
		}

		// Create a SquareMatrix without initalising it's members
		SquareMatrix m = new SquareMatrix();
		// Shallow copy of data as a deep copy is not needed here
		m.assign(data);
		return m;
	}

	/**
	 * Returns the resultant matrix of an elementwise subtraction between SquareMatrix this and SquareMatrix m
	 */
	public SquareMatrix subtract(SquareMatrix m) {
		return subtract(this, m);
	}

	/**
	 * Returns the resultant matrix of an elementwise subtraction between SquareMatrix m1 and SquareMatrix m2
	 */
	public static SquareMatrix subtract(SquareMatrix m1, SquareMatrix m2) {
		if (m1.size != m2.size) {
			throw new IllegalArgumentException("Size of square matrixies m1 and m2 must be equal.");
		}

		int size = m1.size;
		// Zero initalise array
		double[][] data = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				data[i][j] = m1.data[i][j] - m2.data[i][j];
			}
		}

		// Create a SquareMatrix without initalising it's members
		SquareMatrix m = new SquareMatrix();
		// Shallow copy of data as a deep copy is not needed here
		m.assign(data);
		return m;
	}

	/**
	 * Returns the resultant matrix of an inner product between SquareMatrix this and SquareMatrix m
	 */
	public SquareMatrix multiply(SquareMatrix m) {
		return multiply(this, m);
	}

	/**
	 * Returns the resultant matrix of an inner product between SquareMatrix m1 and SquareMatrix m2
	 */
	public static SquareMatrix multiply(SquareMatrix m1, SquareMatrix m2) {
		if (m1.size != m2.size) {
			throw new IllegalArgumentException("Size of square matrixies m1 and m2 must be equal.");
		}

		int size = m1.size;

		// zero initalise data of resultant matrix
		double[][] data = new double[size][size];

		// for each element in the resultant matrix
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				// multiply row i of m1 by column of column j of m2 and add to sum
				for (int k = 0; k < size; k++) {
					data[i][j] += m1.data[i][k] * m2.data[k][j];
				}
			}
		}

		// Create a SquareMatrix without initalising it's members
		SquareMatrix m = new SquareMatrix();
		// Shallow copy of data as a deep copy is not needed here
		m.assign(data);
		return m;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.size; i++) {
			sb.append("[");
			for (int j = 0; j < this.size; j++) {
				if (j != 0) { sb.append(", "); }
				sb.append(this.data[i][j]);
			}
			sb.append("]\n");
		}
		return sb.toString();
	}


}
