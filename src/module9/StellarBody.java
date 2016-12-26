package module9;

import java.awt.Color;

public class StellarBody {

	private double mass;
	private double radius;
	private ThreeVector position;
	private ThreeVector velocity;
	private Color color;

    public StellarBody() {
		this.mass = 1;
		this.radius = 1;
		this.position = ThreeVector.ZERO;
		this.velocity = ThreeVector.ZERO;
		this.color = Color.WHITE;
    }
	
	public StellarBody(double mass, double radius,
			ThreeVector position, ThreeVector veclocity,
			Color color) {
		this.mass = mass;
		this.radius = radius;
		this.position = position;
		this.velocity = veclocity;
		this.color = color;
	}

	public double getMass() {
		return mass;
	}

	public StellarBody setMass(double mass) {
		this.mass = mass;
        return this;
	}

	public double getRadius() {
		return radius;
	}

	public StellarBody setRadius(double radius) {
		this.radius = radius;
        return this;
	}

	public ThreeVector getPosition() {
		return position;
	}

	public StellarBody setPosition(ThreeVector position) {
		this.position = position;
        return this;
	}

	public ThreeVector getVelocity() {
		return velocity;
	}

	public StellarBody setVelocity(ThreeVector velocity) {
		this.velocity = velocity;
        return this;
	}

	public Color getColor() {
		return color;
	}

	public StellarBody setColor(Color color) {
		this.color = color;
        return this;
	}

	public String toString() {
		return "StellarBody: " + position.toString() + " " + radius;
	}

}
