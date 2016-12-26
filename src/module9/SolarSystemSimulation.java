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

    /** default value set to a year every minute, set to 1 for real-time */
    final double timeScale = (60*60*24*365.24) / 60;
    final double spaceScale = 1e-9;

    final long tStep = 5_000_000; // ns per update
    final double stepTimeSeconds = (tStep * 1e-9) * timeScale;

    double simulationTime = 0;
    SolarSystem solarSystem = new SolarSystem();

    JFrame frame;
    JPanel panel;
    SwingWorker<Void, Integer> worker;

    SolarSystemSimulation() {

        StellarBody sun = new StellarBody()
                .setMass(1.989e30) // kg
                .setRadius(695_700_000) // m
                .setColor(Color.YELLOW);
        solarSystem.addBody(sun);

        StellarBody earth = new StellarBody()
                .setMass(5.972e24) // kg
                .setRadius(6_371_000) // m
                .setPosition(new ThreeVector(-149.6e9, 0, 0))
                .setVelocity(new ThreeVector(0, 30_000, 0))
                .setColor(Color.CYAN);
        solarSystem.addBody(earth);

    }

    void start() {

        frame = new JFrame("Solar System Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new SimulationPanel();

        frame.add(panel);       // Add panel to frame
        frame.pack();           // Set component sizes and layout
        frame.setVisible(true); // Display the resulting frame

        worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() {

                long tAccumilator = 0;
                long tprev = System.nanoTime();
                for (;;) {

                    long tcur = System.nanoTime();
                    long dt = tcur - tprev;
                    tAccumilator += dt;
                    tprev = tcur;

                    while (tAccumilator >= tStep) {
                        solarSystem.step(stepTimeSeconds);
                        tAccumilator -= tStep;
                        simulationTime += stepTimeSeconds;
                    }

                    panel.repaint();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        worker.execute();
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

            int stringoff = 1;
            // g2.setColor(Color.WHITE);
            // g2.drawString(cOfM.multiply(spaceScale).toString(), 10, 25);
            // for (StellarBody o : solarSystem.stellarBodies) {
            //     g2.setColor(o.getColor());
            //     g2.drawString(o.getPosition().multiply(spaceScale).toString(),
            //             10, 25*(stringoff++));
            // }
            g2.setColor(Color.WHITE);
            g2.drawString("Simulation time: " + ((int) simulationTime/86400) +
                    " earth days", 10, 25*(stringoff++));

            g2.translate(getWidth() / 2.0, getHeight() / 2.0);
            g2.scale(spaceScale, spaceScale);
            g2.translate(-cOfM.getX(), -cOfM.getY());

            for (StellarBody o : solarSystem.stellarBodies) {
                g2.setColor(o.getColor());
                double radius = 10e9;
                g2.fill(new Ellipse2D.Double(
                         o.getPosition().getX() - radius,
                         o.getPosition().getY() - radius,
                         radius, radius
                         // o.getPosition().getX() - o.getRadius(),
                         // o.getPosition().getY() - o.getRadius(),
                         // 2*o.getRadius(), 2*o.getRadius()
                ));
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() ->
            new SolarSystemSimulation().start()
        );
    }

}

