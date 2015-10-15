package utils;

import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.*;
import static utils.Point.midPoint;
import static utils.Point.point;

/**
 * Author: katooshka
 * Date: 10/16/15.
 */
public class PointTest {

    @Test
    public void testMidPoint(){
        assertPointsEqual(point(11, 3), midPoint(point(10, 2), point(12, 4)));
    }

    private void assertPointsEqual(Point p1, Point p2) {
        assertTrue(String.format("%f %f", p1.getX(), p2.getX()), abs(p1.getX() - p2.getX()) < 0.001);
        assertTrue(String.format("%f %f", p1.getY(), p2.getY()), abs(p1.getY() - p2.getY()) < 0.001);
    }
}