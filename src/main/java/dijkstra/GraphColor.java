package dijkstra;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class GraphColor {
    private final int red;
    private final int green;
    private final int blue;

    public GraphColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public static GraphColor parseColor (String color) {
        if (color == null || color.length() != 6) throw new IllegalArgumentException();
        int red = Integer.parseInt(color.substring(0, 2), 16);
        int green = Integer.parseInt(color.substring(2, 4), 16);
        int blue = Integer.parseInt(color.substring(4, 6), 16);
        return new GraphColor(red, green, blue);
    }
}
