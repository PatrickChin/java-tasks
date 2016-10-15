package module2;

import module2.FallingParticle;

public class ParticleMain {

	public static void main(String[] args) {

		System.out.println("Creating a FallingParticle of mass 6.3 kg and drag coefficient of 4.1");
		FallingParticle p = new FallingParticle(6.3, 4.1);
		
		double[] dts = { 0.5, 0.1, 0.01, 0.001, 0.0001 };
		
		for (double dt : dts) {
			System.out.println("Resetting particle height to 8.0 and velocity to 0.0.");
			p.reset(8.0, 0.0);

			System.out.format("Dropping particle with deltaT = %1$f\n", dt);
			System.out.println("  Initial particle state: " + p);
			
			p.drop(dt);

			System.out.println("    Final particle state: " + p);
			System.out.println();
			System.out.println();
		}
		
	}
}
