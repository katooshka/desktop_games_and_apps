package dijkstra;

import javafx.stage.Screen;

import java.io.IOException;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static java.lang.Math.*;

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

        frame.add(metroMapPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static class MetroMapPanel extends JPanel {
        private static final int STATION_HEIGHT = 10;
        private static final int STATION_WIDTH = 10;
        private static final int LINE_WIDTH = 6;
        private static final int STATION_BORDER = 3;
        private final Graph graph;
        private final List<Edge> edges;
        private final Map<String, Vertex> vertices;
        private final BorderBox borders;

        public MetroMapPanel(Graph graph) {
            this.graph = graph;
            edges = graph.getEdges();
            vertices = graph.getVertices();
            borders = findBorders(graph);
        }

        public static BorderBox findBorders(Graph graph) {
            int top = 0;
            int bottom = 0;
            int left = 0;
            int right = 0;
            boolean first = true;
            for (Vertex value : graph.getVertices().values()){
                int x = value.getCoordinate().getX();
                int y = value.getCoordinate().getY();
                if (first) {
                    first = false;
                    top = y;
                    bottom = y;
                    left = x;
                    right = x;
                    continue;
                }
                top = min(top, y);
                bottom = max(bottom, y);
                left = min(left, x);
                right = max(right, x);
            }
            return new BorderBox(top, bottom, left, right);

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gr = (Graphics2D) g;
            gr.setStroke(new BasicStroke(LINE_WIDTH));
            for (Edge edge : edges) {
                Coordinate firstCoordinate = borders.transformCoordinate(graph.getStartCoordinate(edge), getWidth(), getHeight());
                Coordinate secondCoordinate = borders.transformCoordinate(graph.getEndCoordinate(edge), getWidth(), getHeight());
                gr.drawLine(firstCoordinate.getX(), firstCoordinate.getY(), secondCoordinate.getX(), secondCoordinate.getY());
            }
            gr.setStroke(new BasicStroke(STATION_BORDER));
            for (String vertexName : vertices.keySet()) {
                Vertex vertex = vertices.get(vertexName);
                gr.setColor(awtColor(vertex.getStationColor()));
                Coordinate coordinate = borders.transformCoordinate(vertex.getCoordinate(), getWidth(), getHeight());
                g.fillOval(coordinate.getX() - STATION_WIDTH / 2,
                        coordinate.getY() - STATION_HEIGHT / 2,
                        STATION_WIDTH,
                        STATION_HEIGHT);
                gr.setColor(Color.BLACK);
                g.drawOval(coordinate.getX() - STATION_WIDTH / 2,
                        coordinate.getY() - STATION_HEIGHT / 2,
                        STATION_WIDTH,
                        STATION_HEIGHT);
            }
        }

        private static Color awtColor(GraphColor color) {
            return new Color(color.getRed(), color.getGreen(), color.getBlue());
        }
    }

}

