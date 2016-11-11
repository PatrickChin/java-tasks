package module5;

import java.util.Arrays;

public class SquareMatrix {

	private int size;
	private double[][] data;

	/**
	 * Initalise SquareMatrix without initalising it's members.
	 */
	private SquareMatrix() { }

	public SquareMatrix(int sz) {
		if (sz < 0) {
			throw new IllegalArgumentException("Cannot have a matrix of zise less than zero.");
		}
		size = sz;
		data = new double[size][size];
	}

	/**
	 */
	public SquareMatrix(double[][] elems) {
		this.fill(elems);
	}

	/**
	 * Shallow copy data to this.data.
	 * This may expose this.data
	 */
	public void assign(double[][] elems) {
		this.size = getSize(elems);
		this.data = data;
	}

	/**
	 * Deep copies the argument data to this.data
	 */
	public void fill(double[][] elems) {
		this.size = getSize(elems);
		this.data = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.data[i][j] = elems[i][j];
			}
		}
	}

	public double getAt(int i, int j) {
		return this.data[i][j];
	}

	private static int getSize(double[][] data) {
		if (data.length < 1) {
			throw new IllegalArgumentException("Arguments 'elems' must have a length greater than zero.");
		}
		if (data.length != data[0].length) {
			throw new IllegalArgumentException("Argument 'elems' must have equal 'width' and 'height'.");
		}
		return data.length;
	}

	/**
	 * Create the identity matrix with dimensions size x size.
	 * Throws if size < 0.
	 */
	public SquareMatrix unitMatrix(int size) {
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

	public static SquareMatrix add(SquareMatrix m1, SquareMatrix m2) {
		if (m1.size != m2.size) {
			throw new IllegalArgumentException("Size of matrixies m1 and m2 must be equal.");
		}

		int size = m1.size;

		double[][] data = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				data[i][j] = m1.data[i][j] + m2.data[i][j]
			}
		}

		// Create a SquareMatrix without initalising it's members
		SquareMatrix m = new SquareMatrix();
		m.assign(data);

		return m;
	}

	public static void main(String[] args) {
		SquareMatrix m1 = new SquareMatrix(4);

		{
			double[][] data = {{1,1,1,1},
							   {1,1,1,1},
							   {1,1,1,1},
							   {1,1,1,1}};
			m1.assign(data);

			data[0][0] = 0;
		}

		System.out.println(m1.data[0][0]);

	}

}
