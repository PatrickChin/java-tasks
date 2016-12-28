package module9;

import java.awt.Color;

/**
 * Class representing a named spherical object in space. Can be used to
 * represent planets, stars and asteroids etc.
 */
public class StellarBody {

    private String name;
    private double mass; // kg
    private double radius; // m
    private double dispRadius; // pixels
    private ThreeVector position; // m
    private ThreeVector velocity; // m/s
    private Color color;

    public StellarBody() {
        this.name = new String();
        this.mass = 1;
        this.radius = 1;
        this.dispRadius = 1;
        this.position = ThreeVector.ZERO;
        this.velocity = ThreeVector.ZERO;
        this.color = Color.WHITE;
    }

    // Getters and setters for all parameters

    public String getName() {
        return name;
    }

    public StellarBody setName(String name) {
        this.name = name;
        return this;
    }
    
    public double getMass() {
        return mass;
    }

    public StellarBody setMass(double mass) {
        this.mass = mass;
        return this;
    }

    public double getDispRadius() {
        return dispRadius;
    }

    public StellarBody setDispRadius(double dispRadius) {
        this.dispRadius = dispRadius;
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
