package module1;

public class VectorMethods {

	public /*static*/ double dotProduct(double x1, double y1, double z1, 
			double x2, double y2, double z2) {
		return x1*x2 + y1*y2 + z1*z2;
	}

	public /*static*/ double magnitude(double x, double y, double z) {
		return Math.sqrt(x*x + y*y + z*z);
	}

	public /*static*/ double angle(double x1, double y1, double z1,
			double x2, double y2, double z2) {
		double m1 = magnitude(x1,y1,z1);
		double m2 = magnitude(x2,y2,z2);
		double d = dotProduct(x1,y1,z1,x2,y2,z2);
		return Math.acos(d / m1 / m2);
	}

	public static void main(String[] args) {
		VectorMethods vm = new VectorMethods();
		vm.dotProduct(6,3,1,/**/2,4,2);
		// vm.dotProduct(6,3,1,/**/0,0,0); // cannot divide by 0
	}

}

