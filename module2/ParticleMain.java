package module2;

import module2.FallingParticle;

/**
 * Class to test the effect of using different time steps on the accuracy of the simulation 
 * of a point-like particle (FallingParticle) of a weight of 6.3 kg and a drag coefficient
 * of 4.1 being dropped from a height of 8 meters that is initially at rest.
 */
public class ParticleMain {

	public static void main(String[] args) {

		System.out.println("Creating a FallingParticle of mass 6.3 kg and drag coefficient of 4.1");
		FallingParticle p = new FallingParticle(6.3, 4.1);
		
		// array of time steps to use
		double[] dts = { 0.5, 0.1, 0.01, 0.001, 0.0001 };
		
		for (double dt : dts) {
			System.out.println("Resetting particle height to 8.0 and velocity to 0.0.");
			// resets particle height to 8 m, velocity to 0 m/s and time to 0 s.
			p.reset(8.0, 0.0);

			System.out.format("Dropping particle with deltaT = %1$f\n", dt);
			System.out.println("  Initial particle state: " + p);
			
			p.drop(dt);

			System.out.println("    Final particle state: " + p);
			System.out.println();
			System.out.println();
		}
		
		System.out.println("For smaller and smaller time steps the simulation becomes more " +
				"and more accurate, converging to a t value of around 2.336 and the value " +
				"of z ends closer to that 0.0 mark. The equation of motion is a differential " +
				"equation which becomes more accurate as delta t approaches 0. ");
		
	}
}
