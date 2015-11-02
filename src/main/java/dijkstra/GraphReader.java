package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.*;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class GraphReader {

    public static Graph readGraph(String edgesFilename, String verticesFilename) throws IOException {
        List<Edge> edges = readEdgesFile(edgesFilename);
        Map<String, Vertex> vertices = readVerticesFile(verticesFilename);
        return new Graph(edges, vertices);
    }

    private static List<Edge> readEdgesFile(String filename) throws IOException {
        List<Edge> edges = new ArrayList<>();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] edgesStrings = line.split(",");
                String edgeStart = edgesStrings[0];
                String edgeEnd = edgesStrings[1];
                GraphColor edgeColor = GraphColor.parseColor(edgesStrings[2]);
                int length = parseInt(edgesStrings[3]);
                edges.add(new Edge(edgeStart, edgeEnd, edgeColor, length));
            }
        }
        return edges;
    }

    private static Map<String, Vertex> readVerticesFile(String filename) throws IOException {
        Map<String, Vertex> vertices = new HashMap<>();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] verticesStrings = line.split(",");
                Coordinate coordinate = new Coordinate(parseInt(verticesStrings[1]), parseInt(verticesStrings[2]));
                vertices.put(verticesStrings[0], new Vertex(coordinate, GraphColor.parseColor(verticesStrings[3])));
            }
        }
        return vertices;
    }
}
