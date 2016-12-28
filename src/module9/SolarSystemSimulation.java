package module9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class SolarSystemSimulation {

    /**
     * Ratio between simulation and real time. Default value set to a year
     * every thirty seconds, set to 1 for real-time.
     */
    final double timeScale = (60*60*24*365.24) / 30;

    /**
     * Ratio between distances in the simulation and pixel distances on the
     * display. Default value set to an arbitary constant such that the
     * distance between the sun and the earth is scaled to a few hundred
     * pixels.
     */
    final double spaceScale = 2e-9;

    /** Number of nanoseconds between each step() call */
    final long tStep = 10_000_000; // ns per update
    /** Equivalent time in seconds between each step() call in simulation time. */
    final double stepTimeSeconds = (tStep * 1e-9) * timeScale;

    /** Simulation time in seconds */
    double simulationTime = 0;
    /** Object representing the simulated solar system */
    SolarSystem solarSystem = new SolarSystem(stepTimeSeconds);

    JFrame frame;
    JPanel panel;

    /**
     * Object containing the background thread containing the simulation loop
     * that updates the solar system and repaints the panel.
     */
    SwingWorker<Void, Integer> worker;

    SolarSystemSimulation() {

        frame = new JFrame("Solar System Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new SimulationPanel();

        frame.add(panel);       // Add panel to frame
        frame.pack();           // Set component sizes and layout
        frame.setVisible(true); // Display the resulting frame

        worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() {

                // Accumilated time since last update
                long tAccumilator = 0;
                long tprev = System.nanoTime();

                int stepCounter = 0;
                int fpsCounter = 0;
                long fpsTime = tprev + 1_000_000_000;

                for (;;) {

                    long tcur = System.nanoTime();
                    long dt = tcur - tprev;
                    tAccumilator += dt;
                    tprev = tcur;

                    // Print out fps every second
                    if (tcur > fpsTime) {
                        System.out.println("fps: " + fpsCounter);
                        fpsCounter = 0;
                        fpsTime += 1_000_000_000;
                    }


                    // step the simulation the required number of times
                    while (tAccumilator >= tStep) {
                        // increment the simulation by stepTimeSeconds
                        solarSystem.step();
                        stepCounter++;
                        tAccumilator -= tStep;

                        // Prefer multiplication to summing as this reduces the
                        // floating point errors
                        simulationTime = stepCounter * stepTimeSeconds;
                        // simulationTime += stepTimeSeconds;
                    }

                    fpsCounter++;
                    panel.repaint(); // request the panel to be repainted

                    // This is here so that cpu useage is not 100%
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        worker.execute(); // Start the background worker thread.
    }

    class SimulationPanel extends JPanel {

        SimulationPanel() {
            setPreferredSize(new Dimension(400, 400));
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // call superclass method first


            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                     RenderingHints.VALUE_ANTIALIAS_ON);

            ThreeVector cOfM = solarSystem.getCentreOfMass();

            int yoff = 1;
            g2.setColor(Color.WHITE);
            g2.drawString("Simulation time: " + ((int) simulationTime/86400) +
                    " earth days", 10, 25*(yoff++));

            yoff++;
            for (StellarBody o : solarSystem.getStellarBodies()) {
                g2.setColor(o.getColor());
                g2.fill(new Ellipse2D.Double(20, 25*yoff-10, 10, 10));
                g2.drawString(o.getName(), 40, 25*yoff);

                yoff++;
                // Ignore all further asteroids
                if (o.getName() == "Asteroid") break;
            }


            g2.translate(getWidth() / 2.0, getHeight() / 2.0);
            g2.scale(spaceScale, spaceScale);
            g2.translate(-cOfM.getX(), -cOfM.getY());

            for (StellarBody o : solarSystem.getStellarBodies()) {
                g2.setColor(o.getColor());
                double radius = o.getDispRadius()/spaceScale;
                g2.fill(new Ellipse2D.Double(
                         o.getPosition().getX() - radius,
                         o.getPosition().getY() - radius,
                         2*radius, 2*radius
                ));
            }

        }
    }

    public static void main(String[] args)
            throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeLater(() ->
            new SolarSystemSimulation()
        );
    }

}

