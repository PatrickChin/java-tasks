package module9;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing a group of objects in space that act on eachother through
 * the force of gravity.
 */
public class SolarSystem {

    /** Gravitational constant G in units of: m^3 kg^-1 s^-2 */
    public final static double G = 6.67408e-11; // gravitational constant / m^3 kg^-1 s^-2

    /** Number of seconds to update the simulation by every time step() is called.  */
    private double stepTimeSeconds = 1;

    /** List of all objects in the solar system being simulated */
    private ArrayList<StellarBody> stellarBodies = new ArrayList<>();

    /**
     * Constructor that sets the number of seconds for each step and creates
     * stellar bodies representing the sun, mercury, venus, earth and a comet.
     */
    public SolarSystem(double stepTimeSeconds) {
        this.stepTimeSeconds = stepTimeSeconds;

        // Let the sun be at the origin and the position of further stellar
        // bodies will be relative to the sun
        StellarBody sun = new StellarBody()
                .setName("Sun")
                .setMass(1.989e30) // kg
                .setRadius(695_700_000.0) // m
                .setDispRadius(50)
                .setColor(Color.YELLOW);
        stellarBodies.add(sun);

        StellarBody mercury = new StellarBody()
                .setName("Mercury")
                .setMass(3.285e23) // kg
                .setRadius(2_440_000.0) // m
                .setDispRadius(10)
                .setPosition(new ThreeVector(-46e9, 0, 0))
                .setVelocity(new ThreeVector(0, 58_980, 0))
                .setColor(Color.GRAY);
        stellarBodies.add(mercury);

        StellarBody venus = new StellarBody()
                .setName("Venus")
                .setMass(2.8675e24) // kg
                .setRadius(6_051_800.0) // m
                .setDispRadius(15)
                .setPosition(new ThreeVector(-107e9, 0, 0))
                .setVelocity(new ThreeVector(0, 35_260, 0))
                .setColor(Color.RED);
        stellarBodies.add(venus);

        StellarBody earth = new StellarBody()
                .setName("Earth")
                .setMass(5.972e24) // kg
                .setRadius(6_371_000.0) // m
                .setDispRadius(15)
                .setPosition(new ThreeVector(-149.6e9, 0, 0))
                .setVelocity(new ThreeVector(0, 30_000, 0))
                .setColor(Color.CYAN);
        stellarBodies.add(earth);

        StellarBody comet = new StellarBody()
                .setName("Comet")
                .setMass(1e20) // kg
                .setRadius(6_371_000.0) // m
                .setDispRadius(5)
                .setPosition(new ThreeVector(150e9, 150e9, 0))
                .setVelocity(new ThreeVector(0, -20_000, 0))
                .setColor(Color.GREEN);
        stellarBodies.add(comet);

        // Create 100 asteroids with slight variations in the starting position and speeds
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            StellarBody asteroid = new StellarBody()
                    .setName("Asteroid")
                    .setMass(1e19)
                    .setRadius(1_000)
                    .setDispRadius(2)
                    .setPosition(new ThreeVector(-200e9 + 1e8*rnd.nextGaussian(),
                                1e9*rnd.nextGaussian(), 0))
                    .setVelocity(new ThreeVector(0, 26_000 + 300*rnd.nextGaussian(), 0))
                    .setColor(Color.WHITE);
            stellarBodies.add(asteroid);
        }

    }

    /** Returns the array listo f stellar bodies being simulated */
    public ArrayList<StellarBody> getStellarBodies() {
        return stellarBodies;
    }

    public void step() {
        int n = stellarBodies.size();
        // for (int i = 0; i < n; i++) {
        for (int i = 0; i < n; i++) {
            StellarBody o1 = stellarBodies.get(i);

            // ignore force of planets with mass less than 1x10^23
            if (o1.getMass() > 1e23) {
                for (int j = i+1; j < n; j++) {

                    StellarBody o2 = stellarBodies.get(j);

                    // Vector from o1 to o2
                    ThreeVector r = o2.getPosition().subtract(o1.getPosition());
                    if (r.equals(ThreeVector.ZERO)) continue;
                    double rMagSq = r.magnitudeSq();

                    // gravity equation: F_G = G*m1*m2/r^2

                    // force of o2 on o1
                    o1.setVelocity( o1.getVelocity().add(r.normalise()
                                       .multiply(stepTimeSeconds * G * o2.getMass() / rMagSq)) );

                    // force of o1 on o2
                    o2.setVelocity( o2.getVelocity().add(r.normalise().negate()
                                       .multiply(stepTimeSeconds * G * o1.getMass() / rMagSq)) );
                }
            }

            // update position of stellar object o1
            o1.setPosition(o1.getPosition().add(o1.getVelocity().multiply(stepTimeSeconds)));
        }
    }

    /** Calculates the centre of mass for the system */
    public ThreeVector getCentreOfMass() {
        double totalMass = 0.0;
        ThreeVector cOfM = ThreeVector.ZERO;
        for (StellarBody o : stellarBodies) {
            cOfM = cOfM.add(o.getPosition().multiply(o.getMass()));
            totalMass += o.getMass();
        }
        cOfM = cOfM.divide(totalMass);
        return cOfM;
    }

}

