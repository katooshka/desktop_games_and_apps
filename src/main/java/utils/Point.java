package utils;

/**
 * Author: katooshka
 * Date: 10/16/15.
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public String toString(){
        return String.format("(%.1f, %.1f)", x, y);
    }

    public static Point midPoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public static Point point(double x, double y) {
        return new Point(x, y);
    }
}
