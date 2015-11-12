package dijkstra;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.awt.Color.BLACK;
import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

/**
 * Author: katooshka
 * Date: 11/6/15.
 */
public class MetroMapPanel extends JPanel {
    private static final int STATION_SIZE = 10;
    private static final int MARKED_STATION_SIZE = 20;
    private static final int LINE_WIDTH = 7;
    private static final int LINE_COLORED_WIDTH = 4;
    private static final int STATION_BORDER = 3;
    private static final double SCALE_FACTOR = 1.05;

    private final Graph graph;
    private final List<Edge> edges;
    private final Map<String, Vertex> vertices;
    private BorderBox borders;
    private int oldX;
    private int oldY;
    public String stationFrom = null;
    public String stationTo = null;
    private List<String> way = null;

    public MetroMapPanel(Graph graph) {
        this.graph = graph;
        edges = graph.getEdges();
        vertices = graph.getVertices();
        borders = findBorders(graph).scale(SCALE_FACTOR);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        for (Edge edge : edges) {
            PixelCoordinate firstCoordinate = borders.transformCoordinate(graph.getStartCoordinate(edge), getWidth(), getHeight());
            PixelCoordinate secondCoordinate = borders.transformCoordinate(graph.getEndCoordinate(edge), getWidth(), getHeight());
            gr.setColor(BLACK);
            gr.setStroke(new BasicStroke(LINE_WIDTH));
            gr.drawLine(firstCoordinate.getX(), firstCoordinate.getY(), secondCoordinate.getX(), secondCoordinate.getY());
            gr.setColor(awtColor(edge.getColor()));
            gr.setStroke(new BasicStroke(LINE_COLORED_WIDTH));
            gr.drawLine(firstCoordinate.getX(), firstCoordinate.getY(), secondCoordinate.getX(), secondCoordinate.getY());
        }
        gr.setStroke(new BasicStroke(STATION_BORDER));
        for (String vertexName : vertices.keySet()) {
            drawStation(gr, vertexName, STATION_SIZE);
        }
        if (way != null) {
            drawWay(gr);
        }
        if (stationFrom != null) {
            drawStation(gr, stationFrom, MARKED_STATION_SIZE);
        }
        if (stationTo != null) {
            drawStation(gr, stationTo, MARKED_STATION_SIZE);
        }
    }

    public void onMousePressed(int x, int y) {
        oldX = x;
        oldY = y;
    }

    public void onMouseWheelMoved(double preciseWheelRotation) {
        double scale = Math.pow(SCALE_FACTOR, preciseWheelRotation);
        borders = borders.scale(scale);
        repaint();
    }

    public void onMouseDragged(int x, int y) {
        double dx = -(x - oldX) * borders.getWidth() / getWidth();
        double dy = -(y - oldY) * borders.getHeight() / getHeight();
        borders = borders.move(dx, dy);
        repaint();
        oldX = x;
        oldY = y;
    }

    public void onMouseClicked(int x, int y) {
        if ((stationTo == null && stationFrom == null) || (stationTo != null && stationFrom != null)) {
            stationFrom = getNearestStation(x, y, graph);
            stationTo = null;
            way = null;
        } else {
            stationTo = getNearestStation(x, y, graph);
            way = graph.findShortestWay(stationFrom, stationTo);
        }
        repaint();
    }

    public String getNearestStation(int initialX, int initialY, Graph graph) {
        String currentNearestStation = null;
        double currentNearestWay = POSITIVE_INFINITY;
        for (String vertexName : graph.getVertices().keySet()) {
            Coordinate currentVertexCoordinate = graph.getVertices().get(vertexName).getCoordinate();
            PixelCoordinate currentTransformedVertexCoordinate = borders.transformCoordinate(currentVertexCoordinate,
                    getWidth(), getHeight());
            double possibleNearestWay = Math.sqrt(Math.pow(initialX - currentTransformedVertexCoordinate.getX(), 2) +
                    (Math.pow(initialY - currentTransformedVertexCoordinate.getY(), 2)));
            if (possibleNearestWay < currentNearestWay) {
                currentNearestWay = possibleNearestWay;
                currentNearestStation = vertexName;
            }
        }
        return currentNearestStation;
    }

    public static BorderBox findBorders(Graph graph) {
        int top = 0;
        int bottom = 0;
        int left = 0;
        int right = 0;
        boolean first = true;
        for (Vertex value : graph.getVertices().values()) {
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

    private void drawStation(Graphics2D gr, String vertexName, int stationSize) {
        Vertex vertex = vertices.get(vertexName);
        PixelCoordinate coordinate = borders.transformCoordinate(vertex.getCoordinate(), getWidth(), getHeight());
        paintCircle(gr, stationSize, coordinate, awtColor(vertex.getStationColor()));
    }

    public void drawWay(Graphics2D gr) {
        for (String station : way) {
            drawStation(gr, station, MARKED_STATION_SIZE);
        }
    }

    private void paintCircle(Graphics2D gr, int diameter, PixelCoordinate pixel, Color color) {
        gr.setColor(color);
        gr.fillOval(pixel.getX() - diameter / 2, pixel.getY() - diameter / 2, diameter, diameter);
        gr.setColor(BLACK);
        gr.drawOval(pixel.getX() - diameter / 2, pixel.getY() - diameter / 2, diameter, diameter);
    }

    private static Color awtColor(GraphColor color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }
}
