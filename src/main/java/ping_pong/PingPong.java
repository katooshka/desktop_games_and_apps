package ping_pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Author: katooshka
 * Date: 10/6/15.
 */

public class PingPong {

    public static void main(String[] args) throws InterruptedException{
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(1200, 600));
        frame.setLocationRelativeTo(null);

        MovingParts panel = new MovingParts();
        panel.width = 40;
        panel.height = 40;
        panel.initX = frame.getWidth() / 2 - panel.width / 2;
        panel.x = panel.initX;
        panel.initY = frame.getHeight() / 2 - panel.height / 2;
        panel.y = panel.initY;
        panel.r1x1 = 10;
        panel.r1x2 = 10;
        panel.r2x1 = frame.getWidth() - 10;
        panel.r2x2 = frame.getWidth() - 10;
        panel.r1y1 = 225;
        panel.r1y2 = 375;
        panel.r2y1 = 225;
        panel.r2y2 = 375;


        KeyListener racketMove = new KeyAdapter() {
            private static final double RACKET_SPEED = 500;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    panel.r1vy = -RACKET_SPEED;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    panel.r1vy = RACKET_SPEED;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    panel.r2vy = -RACKET_SPEED;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panel.r2vy = RACKET_SPEED;
                }
            }

            @Override
            public void keyReleased(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S){
                    panel.r1vy = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
                    panel.r2vy = 0;
                }
            }
        };
        frame.addKeyListener(racketMove);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        initVelocity(panel);

        int tms = 10;
        for (int i = 0; i < 20000; i++) {
            double dt = tms / 1000.;

            int r1DownReturn = 0;
            int r1UpReturn = 0;
            int r2DownReturn = 0;
            int r2UpReturn = 0;

            if (panel.r1y1 < 0){
                panel.r1vy = 0;
                r1DownReturn = 1;
            }
            if (panel.r1y2 > frame.getHeight()){
                panel.r1vy = 0;
                r1UpReturn = 1;
            }
            if (panel.r2y1 < 0){
                panel.r2vy = 0;
                r2DownReturn = 1;
            }
            if (panel.r2y2 > frame.getHeight()){
                panel.r2vy = 0;
                r2UpReturn = 1;
            }

            if (panel.x < 0 || panel.x > frame.getWidth()){
                panel.x = panel.initX;
                panel.y = panel.initY;
                initVelocity(panel);
            }
            if (panel.x + panel.width > panel.r2x1 && panel.y <= panel.r2y2 && panel.y >= panel.r2y1){
                if (panel.vx > 0){
                    panel.vx = (-1) * panel.vx;
                }
            }
            if (panel.x < panel.r1x1 && panel.y <= panel.r1y2 && panel.y >= panel.r1y1){
                if (panel.vx < 0){
                    panel.vx = (-1) * panel.vx;
                }
            }
            if (panel.y < 0 || panel.y > frame.getHeight() - panel.height) {
                panel.vy *= -1;
            }

            panel.r1y1 += panel.r1vy * dt + r1DownReturn - r1UpReturn;
            panel.r1y2 += panel.r1vy * dt + r1DownReturn - r1UpReturn;
            panel.r2y1 += panel.r2vy * dt + r2DownReturn - r2UpReturn;
            panel.r2y2 += panel.r2vy * dt + r2DownReturn - r2UpReturn;

            panel.x += panel.vx * dt;
            panel.y += panel.vy * dt;

            SwingUtilities.invokeLater(panel::repaint);
            Thread.sleep(tms);
        }
    }

    private static void initVelocity(MovingParts panel) {
        double alpha = Math.random() * Math.PI / 2 - Math.PI / 4;
        Random random = new Random();
        double side = random.nextBoolean() ? 1 : -1;
        panel.vx = 500 * Math.cos(alpha) * side;
        panel.vy = 500 * Math.sin(alpha) * side;
    }

    public static class MovingParts extends JPanel{
        protected int width;
        protected int height;
        protected double x;
        protected double y;
        protected double vx;
        protected double vy;
        protected double ax;
        protected double ay;

        protected int initX;
        protected int initY;

        protected double r1x1;
        protected double r1y1;
        protected double r1x2;
        protected double r1y2;
        protected double r1vy;

        protected double r2x1;
        protected double r2y1;
        protected double r2x2;
        protected double r2y2;
        protected double r2vy;

        @Override
        public void paintComponent(Graphics g){
            super.paintComponents(g);
            Graphics2D gr = (Graphics2D)g;
            BasicStroke pen = new BasicStroke(5);
            gr.setStroke(pen);
            gr.setColor(new Color(46, 67, 123));
            gr.drawOval((int) x, (int) y, width, height);
            gr.setColor(new Color(61, 85, 168));
            gr.fillOval((int) x, (int) y, width, height);
            gr.setColor(new Color(91, 13, 91));
            gr.drawLine((int) r1x1, (int) r1y1, (int) r1x2, (int) r1y2);
            gr.drawLine((int) r2x1, (int) r2y1, (int) r2x2, (int) r2y2);
        }
    }

}
