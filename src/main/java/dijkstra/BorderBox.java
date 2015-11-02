package dijkstra;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class BorderBox {
    private int top;
    private int bottom;
    private int left;
    private int right;

    public BorderBox(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public Coordinate transformCoordinate(Coordinate initialCoordinate, int width, int height) {
        int x = width * (initialCoordinate.getX() - left) / (right - left);
        int y = height * (initialCoordinate.getY() - top) / (bottom - top);
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return "BorderBox{" +
                "top=" + top +
                ", bottom=" + bottom +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
