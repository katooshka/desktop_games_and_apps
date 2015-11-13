package avatar_generator;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static avatar_generator.HashGetter.*;
import static java.lang.Integer.*;

/**
 * Author: katooshka
 * Date: 11/8/15.
 */
public class AvatarGenerator {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        drawImage(generateHash("dhgdhvdgvпвпвпв667нр778799909((&6%:LKJHGFD"), 4);
    }

    public static void drawImage(String hash, int size) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(400, 400));

        ImagePanel panel = new ImagePanel(hash, size);
        panel.setBackground(Color.WHITE);

        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static Color getColorFromHash (String hash, boolean reverse) {
        if (reverse) {
            return (new Color(parseInt(hash.substring(0, 2), 16),
                    parseInt(hash.substring(2, 4), 16),
                    Integer.parseInt(hash.substring(4, 6), 16)));
        } else {
            return (new Color(parseInt(hash.substring(8, 10), 16),
                    parseInt(hash.substring(3, 5), 16),
                    Integer.parseInt(hash.substring(5, 7), 16)));
        }

    }

    public static class ImagePanel extends JPanel {
        private String hashString;
        private int figureNumber;

        public ImagePanel(String hashString, int figureSize) {
            this.hashString = hashString;
            this.figureNumber = figureSize;
        }

        @Override
        public void paintComponent(Graphics gr) {
            Graphics2D g = (Graphics2D)gr;
            Random random = new Random();
            super.paintComponent(gr);
            int multiplier = getHeight() / ((figureNumber * 2) - 1);
            Color borderColor = getColorFromHash(hashString, true);
            Color fillColor = getColorFromHash(hashString, false);
            g.setStroke(new BasicStroke(4));
            for (int x = 0; x < figureNumber; x++) {
                for (int y = 0; y < figureNumber * 2; y++) {
                    if (random.nextBoolean()){
                        gr.setColor(fillColor);
                        gr.fillRect(x * multiplier, y * multiplier, multiplier, multiplier);
                        gr.fillRect(getWidth() - (x + 1) * multiplier, y * multiplier, multiplier, multiplier);
                    }
                    g.setColor(borderColor);
                    g.drawRect(x * multiplier, y * multiplier, multiplier, multiplier);
                    g.drawRect(getWidth() - (x + 1) * multiplier, y * multiplier, multiplier, multiplier);
                }
            }
        }
    }
}
