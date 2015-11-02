package dijkstra;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class Edge {
    private String firstStation;
    private String secondStation;
    private GraphColor color;
    private int length;

    public Edge(String firstStation, String secondStation, GraphColor color, int length) {
        this.firstStation = firstStation;
        this.secondStation = secondStation;
        this.color = color;
        this.length = length;
    }

    public String getFirstStation() {
        return firstStation;
    }

    public String getSecondStation() {
        return secondStation;
    }

    public GraphColor getColor() {
        return color;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "firstStation='" + firstStation + '\'' +
                ", secondStation='" + secondStation + '\'' +
                ", color='" + color + '\'' +
                ", length=" + length +
                '}';
    }
}
