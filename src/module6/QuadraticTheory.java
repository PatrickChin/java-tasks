package module6;

public class QuadraticTheory implements Theory {

	private double a, b, c;
	
	public QuadraticTheory(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public double y(double x) {
		return a*x*x + b*x + c;
	}

	@Override
	public String toString() {
		return String.format("%fx^2 + %fx + %f", a, b, c);
	}

}
