package module3;

/**
 * Class that simulates a point particle falling through a viscous medium on
 * Earth's surface
 */
public class FallingParticle {

	/** Acceleration due to gravity on Earth's surface. */
	public static final double g = 9.8;

	/** Mass of falling particle (unchangeable). */
	public final double m;
	
	/** Drag coefficient of the particle (unchangeable). */
	public final double d;

	/**
	 * Vertical position of the particle, measured upwards from the base of the
	 * vessel.
	 */
	private double z;

	/** Velocity of the particle measured upwards. */
	private double v;

	/** Time in seconds since the particle was dropped. */
	private double t;

	/**
	 * Construct a falling particle and set it's mass and drag coefficient.
	 * Throws if mass < 0.
	 */
	public FallingParticle(double mass, double drag) { 
		if (mass <= 0.0) {
			throw new IllegalArgumentException("Cannot set mass to less than 0");
		}
		this.m = mass;
		this.d = drag;
	}

	/**
	 * Set the vertical position of the particle.
	 * Throws if z < 0.
	 */
	public void setZ(double z) {
		if (z < 0.0) {
			throw new IllegalArgumentException("Cannot set height to a value to less than 0");
		}
		this.z = z;
	}

	/** Get the vertical position of the particle. */
	public double getZ() {
		return z;
	}

	/** Set the vertical velocity of the particle. */
	public void setV(double v) {
		this.v = v;
	}

	/** Get the vertical velocity of the particle. */
	public double getV() {
		return v;
	}

	/** Reset the time elapsed since the particle was dropped. */
	public void reset(double z, double v) {
		setZ(z);
		// this.z = z;
		this.v = v;
		this.t = 0.0;
	}

	/** Get the time elapsed since the particle was dropped. */
	public double getT() {
		return t;
	}

	/**
	 * Simulate particle falling for a small time deltaT.
	 * Where the drag is proportional to the square of the velocity.
	 * For the time period deltaT:
	 *  - the calculation of the acceleration assumes constant velocity
	 *  - the calculation of the velocity and distance assumes constant acceleration
	 */
	public void doTimeStep(double dt) {
		double a = (d*v*v/m) - g;
		v += a*dt;
		z += v*dt + a*dt*dt/2.0;
		t += dt;
	}
	
	/**
	 * Start the simulation of dropping this particle from a height of z in
	 * time steps of dt and return after the particle has hit the ground.
	 */
	public void drop(double dt) {
		while (z > 0.0) {
			doTimeStep(dt);
		}
	}

	public String toString() {
		return String.format("m=%1$.2f, d=%2$.2f, z=%3$.5f, v=%4$.4f, t=%5$.4f", m, d, z, v, t);
	}

}
