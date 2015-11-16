package dijkstra;

import java.util.*;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class Graph {
    private List<Edge> edges;
    private Map<String, Vertex> vertices;
    private int minWayLength;

    public Graph(List<Edge> edges, Map<String, Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Map<String, Vertex> getVertices() {
        return vertices;
    }

    public Coordinate getStartCoordinate(Edge edge) {
        return vertices.get(edge.getFirstStation()).getCoordinate();
    }

    public Coordinate getEndCoordinate(Edge edge) {
        return vertices.get(edge.getSecondStation()).getCoordinate();
    }

    public ShortestWay findShortestWay(String stationFrom, String stationTo) {
        ShortestWay way = getWay(stationFrom, stationTo);
        minWayLength = way.getWayLength();
        return way;
    }

    public List<Edge> getVertexEdges(String vertex) {
        List<Edge> vertexEdges = new ArrayList<>();
        for (Edge edge : edges) {
            if (vertex.equals(edge.getFirstStation())) {
                vertexEdges.add(edge);
            }
        }
        return vertexEdges;
    }

    Map<String, Integer> currentMinimalDistance = new HashMap<>();
    Map<String, Integer> finalDistance = new HashMap<>();
    Set<String> visitedStations = new HashSet<>();
    List<Edge> currentQueue = new ArrayList<>();
    Map<String, String> parentStations = new HashMap<>();

    public ShortestWay getWay(String stationFrom, String stationTo) {
        clearVariables();
        setInitialDistances(stationFrom);
        while (!visitedStations.contains(stationTo)) {
            String currentStation = getNextVertex();
            addNewEdges(currentStation);
            int queueSize = currentQueue.size();
            for (int i = 0; i < queueSize; i++) {
                setNewMinimalDistance(getMinLengthEdge(currentQueue));
                currentQueue.remove(getMinLengthEdge(currentQueue));
            }
            visitedStations.add(currentStation);
            finalDistance.put(currentStation, currentMinimalDistance.get(currentStation));
            currentMinimalDistance.remove(currentStation);
        }
        return new ShortestWay(getStations(stationFrom, stationTo), finalDistance.get(stationTo));
    }

    private void clearVariables() {
        currentMinimalDistance.clear();
        finalDistance.clear();
        visitedStations.clear();
        parentStations.clear();
    }

    private String getNextVertex() {
        String minDistanceStation = currentMinimalDistance.entrySet().iterator().next().getKey();
        for (String station : currentMinimalDistance.keySet()) {
            if (currentMinimalDistance.get(station) < currentMinimalDistance.get(minDistanceStation)) {
                minDistanceStation = station;
            }
        }
        return minDistanceStation;
    }

    private void setInitialDistances(String stationFrom) {
        for (String vertex : vertices.keySet()) {
            currentMinimalDistance.put(vertex, (int) Double.POSITIVE_INFINITY);
        }
        currentMinimalDistance.put(stationFrom, 0);
    }

    private Edge getMinLengthEdge(List<Edge> currentQueue) {
        int minEdgeLength = currentQueue.get(0).getLength();
        Edge minLengthEdge = currentQueue.get(0);
        for (Edge edge : currentQueue) {
            if (edge.getLength() < minEdgeLength) {
                minEdgeLength = edge.getLength();
                minLengthEdge = edge;
            }
        }
        return minLengthEdge;
    }

    private void setNewMinimalDistance(Edge minLengthEdge) {
        if (currentMinimalDistance.get(minLengthEdge.getFirstStation()) + minLengthEdge.getLength()
                < currentMinimalDistance.get(minLengthEdge.getSecondStation())) {
            currentMinimalDistance.put(minLengthEdge.getSecondStation(),
                    currentMinimalDistance.get(minLengthEdge.getFirstStation()) + minLengthEdge.getLength());
            parentStations.put(minLengthEdge.getSecondStation(), minLengthEdge.getFirstStation());
        }
    }

    private void addNewEdges(String station) {
        for (Edge edge : getVertexEdges(station)) {
            if (!visitedStations.contains(edge.getSecondStation())) {
                currentQueue.add(edge);
            }
        }
    }

    private List<String> getStations(String stationFrom, String stationTo) {
        List<String> way = new ArrayList<>();
        way.add(stationTo);
        String currentStation = stationTo;
        while (!way.contains(stationFrom)) {
            way.add(parentStations.get(currentStation));
            currentStation = parentStations.get(currentStation);
        }
        Collections.reverse(way);
        return way;
    }

    public int getMinWayLength() {
        return minWayLength;
    }
}
