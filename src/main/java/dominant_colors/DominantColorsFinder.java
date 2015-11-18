package dominant_colors;

import avatar_generator.AvatarGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author: katooshka
 * Date: 11/20/15.
 */
public class DominantColorsFinder {

    public static void main(String args[]) {
        drawFrame();
    }

    public static void drawFrame(){
        JFrame frame = new JFrame();
        ImagePanel panel = new ImagePanel();
        panel.setPreferredSize(new Dimension(700, 700));

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openFileMenu = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openFileMenu);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        openFileMenu.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG or PNG images", "jpg", "png");
            chooser.setFileFilter(filter);
            int returnValue = chooser.showOpenDialog(openFileMenu); //что это значит?
            if (returnValue == JFileChooser.APPROVE_OPTION){        //что это значит?
                try {
                    panel.image = ImageIO.read(new File(chooser.getSelectedFile().getPath()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        exitItem.addActionListener(e -> System.exit(0));

        frame.add(panel);
        frame.pack();
        frame.setJMenuBar(menuBar);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static class ImagePanel extends JPanel {
        private BufferedImage image;

        @Override
        public void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            gr.drawImage(image, 0, 0, null);
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color pixelColor = new Color(image.getRGB(i, j));
                    System.out.println(pixelColor.getRed());
                    System.out.println(pixelColor.getGreen());
                    System.out.println(pixelColor.getBlue());
                }
            }
            this.repaint();
        }
    }
}
