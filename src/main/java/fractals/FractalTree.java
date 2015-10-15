package fractals;


import utils.Point;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static utils.Point.point;

/**
 * Author: katooshka
 * Date: 10/16/15.
 */

public class FractalTree {
    private static final Point A = point(350, 650);
    private static final Point B = point(350, 400);
    private static final int DEPTH = 12;
    private static final int BRUSH = 20;
    private static final Dimension PANEL_SIZE = new Dimension(700, 700);

    BranchesPanel tree = null;

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        new FractalTree();
    }

    public FractalTree() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            JFrame frame = new JFrame();
            tree = new BranchesPanel();
            tree.setPreferredSize(PANEL_SIZE);
            frame.add(tree);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static class BranchesPanel extends JPanel {
        private double beta = PI / 2.5;
        private double mu = 0.65;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintTree(g, A, B, mu, DEPTH, BRUSH, new Color(60, 0, 0), new Color(0, 255, 0));
        }

        private void paintTree(Graphics g, Point a, Point b, double m, int depth, int brush, Color start, Color end) {
            g.setColor(new Color(91, 13, 91));
            drawLine(g, a, b, brush);
            if (depth > 0) {
                Point n = findEndCoordinates(a, b, beta, m);
                Point l = findEndCoordinates(a, b, -beta, m);
                paintTree(g, b, n, m, depth - 1, brush / 2, start, end);
                paintTree(g, b, l, m, depth - 1, brush / 2, start, end);
            }
        }

        private static Point findEndCoordinates(Point a, Point b, double beta, double m) {
            double x1 = b.getX() + m * (cos(beta) * (b.getX() - a.getX()) - sin(beta) * (b.getY() - a.getY()));
            double y1 = b.getY() + m * (sin(beta) * (b.getX() - a.getX()) + cos(beta) * (b.getY() - a.getY()));
            return new Point(x1, y1);
        }

        private static void drawLine(Graphics gr, Point a, Point b, int brush){
            Graphics2D g2 = (Graphics2D)gr;
            BasicStroke pen = new BasicStroke(brush);
            g2.setStroke(pen);
            g2.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
        }
    }
}