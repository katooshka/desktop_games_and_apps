package dijkstra;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class Vertex {
    private Coordinate coordinate;
    private GraphColor stationColor;

    public Vertex(Coordinate coordinate, GraphColor stationColor) {
        this.coordinate = coordinate;
        this.stationColor = stationColor;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public GraphColor getStationColor() {
        return stationColor;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "coordinate=" + coordinate +
                ", stationColor='" + stationColor + '\'' +
                '}';
    }
}
