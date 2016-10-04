package module1;

public class VectorMethods {

	// No reason for these not to be static

	public /*static*/ double dotProduct(double x, double y, double z, 
			double x2, double y2, double z2) {
		return x*x2 + y*y2 + z*z2;
	}

	public /*static*/ double magnitude(double x, double y, double z) {
		return Math.sqrt(x*x + y*y + z*z);
	}

	public /*static*/ double angle(double x, double y, double z, 
			double x2, double y2, double z2) {
		double m1 = magnitude(x,y,z);
		double m2 = magnitude(x2,y2,z2);
		double d = dotProduct(x,y,z,x2,y2,z2);
		return Math.acos(d / m1 / m2);
	}


	public static void main(String[] args)
	{
		VectorMethods vm = new VectorMethods();
		vm.dotProduct(6,3,1,/**/2,4,2);
		// vm.dotProduct(6,3,1,/**/0,0,0); // cannot divide by 0
	}

}
