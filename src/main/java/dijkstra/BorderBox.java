package dijkstra;

/**
 * Author: katooshka
 * Date: 11/2/15.
 */
public class BorderBox {
    private double top;
    private double bottom;
    private double left;
    private double right;

    public BorderBox(double top, double bottom, double left, double right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public double getTop() {
        return top;
    }

    public double getBottom() {
        return bottom;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getWidth() {
        return right - left;
    }

    public double getHeight() {
        return bottom - top;
    }

    public PixelCoordinate transformCoordinate(Coordinate initialCoordinate, int width, int height) {
        int x = (int) Math.round(width * (initialCoordinate.getX() - left) / getWidth());
        int y = (int) Math.round(height * (initialCoordinate.getY() - top) / getHeight());
        return new PixelCoordinate(x, y);
    }

    public BorderBox scale(double mul) {
        double centerY = (bottom + top) / 2;
        double centerX = (right + left) / 2;
        double newTop = centerY - (getHeight() / 2) * mul;
        double newBottom = centerY + (getHeight() / 2) * mul;
        double newLeft = centerX - (getWidth() / 2) * mul;
        double newRight = centerX + (getWidth() / 2) * mul;
        return new BorderBox(newTop, newBottom, newLeft, newRight);
    }

    public BorderBox move (double dx, double dy) {
        return new BorderBox(top + dy, bottom + dy, left + dx, right + dx);
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
