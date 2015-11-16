package dijkstra;

import java.util.List;

/**
 * Author: katooshka
 * Date: 11/16/15.
 */
public class ShortestWay {
    private List<String> stations;
    private int wayLength;

    public ShortestWay(List<String> stations, int wayLength) {
        this.stations = stations;
        this.wayLength = wayLength;
    }

    public List<String> getStations() {
        return stations;
    }

    public int getWayLength() {
        return wayLength;
    }
}
