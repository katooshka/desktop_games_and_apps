package game_of_life;

import javax.swing.*;
import java.awt.*;

/**
 * Author: katooshka
 * Date: 11/11/15.
 */

public class GameOfLife {
    //TODO: SwingUtilities.invokeLater() после всего остального
    //TODO: убрать 20000
    //TODO: сделать тест


    private static final int FIELD_SIZE = 700;
    private static final int CELL_SIZE = 10;
    private static final int CELLS_DENSITY_PERCENTAGE = 30;
    private static final Color CELL_COLOR = Color.ORANGE;
    private static Field field = new Field(FIELD_SIZE / CELL_SIZE, CELLS_DENSITY_PERCENTAGE);

    public static void main(String[] args) throws InterruptedException {
        drawFrame();
    }

    public static void drawFrame() throws InterruptedException {
        JFrame frame = new JFrame();
        GamePanel panel = new GamePanel();
        frame.setContentPane(panel);
        panel.setPreferredSize(new Dimension(FIELD_SIZE, FIELD_SIZE));
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int tms = 20;
        for (int i = 0; i < 20000; i++) {
            field.nextGeneration();
            panel.repaint();
            Thread.sleep(tms);
        }
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



