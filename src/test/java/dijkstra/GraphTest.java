package dijkstra;

import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import static dijkstra.GraphReader.*;

/**
 * Author: katooshka
 * Date: 11/16/15.
 */
public class GraphTest {

    public static void main(String[] args) throws IOException {
    }

    @Test
    public void testWayLength() throws IOException {
        Map<String, Vertex> vertices = new HashMap<>();
        vertices.put("A", null);
        vertices.put("B", null);
        vertices.put("C", null);
        List<Edge> edges = asList(
                new Edge("A", "B", null, 3),
                new Edge("B", "A", null, 3),
                new Edge("A", "C", null, 10),
                new Edge("C", "A", null, 10),
                new Edge("B", "C", null, 4),
                new Edge("C", "B", null, 4));
        Graph graph = new Graph(edges, vertices);
        assertEquals(3, graph.getWay("A", "B"));
        assertEquals(3, graph.getWay("B", "A"));
        assertEquals(4, graph.getWay("C", "B"));
        assertEquals(4, graph.getWay("B", "C"));
        assertEquals(7, graph.getWay("A", "C"));
        assertEquals(7, graph.getWay("C", "A"));

        assertEquals(asList("B", "A"), graph.findShortestWay("B", "A"));
        assertEquals(asList("A", "B", "C"), graph.findShortestWay("A", "C"));
    }

}
