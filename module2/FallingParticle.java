package module2;

public class FallingParticle {

	private double m;
	private double z;
	private double v;
	private long t;
	
	public static double d = 1.0;
	public static double g = 9.8;

	public FallingParticle(double m, double d, double z, double v) { 
		this.m = m;
		this.d = d;
		this.z = z;
		this.v = v;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getM() {	
		return m;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getD() {	
		return d;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getZ() {	
		return z;
	}

	public void setV(double v) {
		this.v = v;
	}

	public double getV() {	
		return v;
	}

	public double getT() {	
		return t;
	}

}
