package catchme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Author: katooshka
 * Date: 9/26/15.
 */
public class CatchMe {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(500, 500));
        JButton button = new JButton("Push me");
        button.addActionListener(e -> button.setText("Pushed-in"));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Random random = new Random();
                button.setLocation(random.nextInt(500 - button.getWidth()), random.nextInt(500 - button.getHeight()));
            }
        });
        JPanel panel = new JPanel();
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
