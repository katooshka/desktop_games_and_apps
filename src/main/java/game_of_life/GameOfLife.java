package game_of_life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Author: katooshka
 * Date: 11/11/15.
 */

public class GameOfLife {
    //TODO: сделать тест

    private static final int CELL_SIZE = 7;
    private static final int CELL_COUNT = 100;
    private static final int FIELD_SIZE = CELL_SIZE * CELL_COUNT;
    private static final int CELLS_DENSITY_PERCENTAGE = 30;
    private static final Color CELL_COLOR = new Color(21, 109, 127);
    private static Field field = Field.createField(CELL_COUNT, CELLS_DENSITY_PERCENTAGE);
    private static volatile boolean stopped = false;
    private static JFrame frame = new JFrame();
    private static GamePanel panel = new GamePanel();

    public static void main(String[] args) throws InterruptedException {
        drawFrame();
    }

    public static void drawFrame() throws InterruptedException {
        SwingUtilities.invokeLater(() -> {
            frame.setContentPane(panel);
            panel.setPreferredSize(new Dimension(FIELD_SIZE, FIELD_SIZE));
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    stopped = true;
                }
            });
        });

        int tms = 80;
        while (!stopped) {
            Thread.sleep(tms);
            SwingUtilities.invokeLater(() -> {
                field.nextGeneration();
                panel.repaint();
            });
        }
        SwingUtilities.invokeLater(frame::dispose);

    }

    public static class GamePanel extends JPanel {

        @Override
        public void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            drawField(gr);
            drawCells(gr, field.getCells());
        }

        public static void drawField(Graphics gr) {
            gr.setColor(Color.GRAY);
            for (int x = 0; x < FIELD_SIZE; x += CELL_SIZE) {
                for (int y = 0; y < FIELD_SIZE; y += CELL_SIZE) {
                    gr.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        public static void drawCells(Graphics gr, boolean[][] cells) {
            gr.setColor(CELL_COLOR);
            for (int x = 0; x < FIELD_SIZE; x += CELL_SIZE) {
                for (int y = 0; y < FIELD_SIZE; y += CELL_SIZE) {
                    if (cells[x / CELL_SIZE][y / CELL_SIZE]) {
                        gr.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                    }
                }
            }
        }
    }
}



