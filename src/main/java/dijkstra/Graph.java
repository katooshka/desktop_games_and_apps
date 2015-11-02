package dijkstra;

import java.util.List;
import java.util.Map;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class Graph {
    private List<Edge> edges;
    private Map<String, Vertex> vertices;

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
}
