package module6;

public class LabelledDataPoint extends DataPoint {
	
	String label;

	public LabelledDataPoint(double x, double y, double ey, String label) {
		super(x, y, ey);
		this.label = label;
	}

	@Override
	public String toString() {
		return label + ": " + super.toString();
	}
	
}
