package dijkstra;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class Frame {

    public static void main(String[] args) throws IOException {
        Graph graph = GraphReader.readGraph("dijkstra/edges.txt", "dijkstra/vertices.txt");
        drawFrame(graph);
    }

    public static void drawFrame(Graph graph) throws IOException {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 900));

        MetroMapPanel metroMapPanel = new MetroMapPanel(graph);

        metroMapPanel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                metroMapPanel.onMouseWheelMoved(e.getPreciseWheelRotation());
            }
        });
        metroMapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                metroMapPanel.onMousePressed(e.getX(), e.getY());
            }
        });
        metroMapPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                metroMapPanel.onMouseDragged(e.getX(), e.getY());
            }
        });
        metroMapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                metroMapPanel.onMouseClicked(e.getX(), e.getY());
            }
        });

        frame.add(metroMapPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

