package fractals;

import utils.Point;

import javax.swing.*;
import java.awt.*;

import static utils.Point.midPoint;
import static utils.Point.point;

/**
 * Author: katooshka
 * Date: 10/8/15.
 */
public class SierpinskiTriangle {
    private static final int TRIANGLE_WIDTH = 700;
    private static final int TRIANGLE_HEIGHT = (int) (TRIANGLE_WIDTH * Math.sqrt(3) / 2);
    private static final int BORDER = 10;
    private static final int DEPTH = 6;

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Triangles triangles = new Triangles();
        triangles.setPreferredSize(new Dimension(TRIANGLE_WIDTH + BORDER * 2, TRIANGLE_HEIGHT + BORDER * 2));

        triangles.setBackground(new Color(255, 77, 74));
        frame.add(triangles);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static class Triangles extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintFilledTriangle(g,
                    point(TRIANGLE_WIDTH / 2 + BORDER, BORDER),
                    point(BORDER, TRIANGLE_HEIGHT + BORDER),
                    point(TRIANGLE_WIDTH + BORDER, TRIANGLE_HEIGHT + BORDER),
                    new Color(255, 77, 74),
                    new Color(21, 109, 127));
        }

        private static void paintFilledTriangle(Graphics g, Point u, Point l, Point r,
                                                Color fillColor, Color paintColor) {
            g.setColor(fillColor);
            paintTriangle(g, u, l, r);
            g.setColor(paintColor);
            paintFractal(g, u, l, r, DEPTH);
        }

        private static void paintFractal(Graphics g, Point u, Point l, Point r, int depth) {
            if (depth == 0) {
                paintTriangle(g, u, l, r);
                return;
            }

            Point ul = midPoint(u, l);
            Point ur = midPoint(u, r);
            Point d = midPoint(l, r);
            paintFractal(g, u, ul, ur, depth - 1);
            paintFractal(g, ul, l, d, depth - 1);
            paintFractal(g, ur, d, r, depth - 1);
        }


        private static void paintTriangle(Graphics g, Point a, Point b, Point c) {
            int[] xs = {(int) a.getX(), (int) b.getX(), (int) c.getX()};
            int[] ys = {(int) a.getY(), (int) b.getY(), (int) c.getY()};
            g.fillPolygon(xs, ys, 3);
        }

    }
}
