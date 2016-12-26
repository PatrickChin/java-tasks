package module9;

import java.util.ArrayList;

public class SolarSystem {

    // public final static double G = 1; // gravitational constant
    public final static double G = 6.67408e-11; // gravitational constant / m^3 kg^-1 s^-2

    /** default value set to a year every minute, set to 1 for real-time */
    double timeScale = (60*60*24*365.24) / 60;
    double spaceScale = 1e-9;

    ArrayList<StellarBody> stellarBodies = new ArrayList<>();

    public void addBody(StellarBody o) {
        stellarBodies.add(o);
    }

    public void step(long nsStep) {
        // double stepTimeSeconds = 1;
        double stepTimeSeconds = (nsStep * 1e-9) * timeScale;
        int n = stellarBodies.size();
        for (int i = 0; i < n; i++) {
            StellarBody o1 = stellarBodies.get(i);

            for (int j = i+1; j < n; j++) {
                StellarBody o2 = stellarBodies.get(j);

                ThreeVector r = o2.getPosition().subtract(o1.getPosition());
                if (r.equals(ThreeVector.ZERO)) continue;
                double rMagSq = r.magnitudeSq();

                o1.setVelocity( o1.getVelocity().add(r.normalise()
                                   .multiply(stepTimeSeconds * G * o2.getMass() / rMagSq)) );
                o2.setVelocity( o2.getVelocity().add(r.normalise().negate()
                                   .multiply(stepTimeSeconds * G * o1.getMass() / rMagSq)) );
            }
            o1.setPosition(o1.getPosition().add(o1.getVelocity().multiply(stepTimeSeconds)));
        }
    }

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

