package fly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.lang.ClassLoader.getSystemResource;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Author: katooshka
 * Date: 10/17/15.
 */
public class Fly {
    private static final double SPEED = 400;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(600, 600));
        frame.setLocationRelativeTo(null);
        PaintPoint point = new PaintPoint();
        point.x = 500;
        point.y = 60;

        point.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                point.cursorIsSet = true;
                point.mx = e.getX();
                point.my = e.getY();
            }
        });

        frame.add(point);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int tms = 20;
        for (int i = 0; i < 20000; i++) {
            double dt = tms / 1000.;
            double pathLength = sqrt(pow((point.mx - point.x), 2) + pow((point.my - point.y), 2));

            if (pathLength < 5 || !point.cursorIsSet) {
                point.vx = 0;
                point.vy = 0;
            } else {
                point.vx = (point.mx - point.x) / pathLength;
                point.vy = (point.my - point.y) / pathLength;
            }

            point.x += point.vx * SPEED * dt;
            point.y += point.vy * SPEED * dt;

            point.repaint();
            Thread.sleep(tms);
        }
    }

    public static class PaintPoint extends JPanel {
        private Image flyImage = Toolkit.getDefaultToolkit().getImage(getSystemResource("fly/fly.png"));

        public boolean cursorIsSet = false;
        public double mx;
        public double my;
        public double x;
        public double y;
        public double vx;
        public double vy;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gr = (Graphics2D) g;
            gr.rotate(Math.atan2((my - y), (mx - x)), (int) x, (int) y);
            gr.drawImage(flyImage, (int) x - flyImage.getWidth(this) / 2, (int) y - flyImage.getHeight(this) / 2, this);
        }
    }
}