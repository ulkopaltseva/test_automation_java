package ru.ulko.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by yulia on 20.08.2019.
 */
public class PointTests {

    @Test
    public void testDistancePositive() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 6);
        double d = Math.sqrt((18));
        Assert.assertEquals(p1.distance(p1,p2), d);
    }

    @Test
    public void testDistanceNegative() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(5, 6);
        double d = Math.sqrt((18));
        Assert.assertEquals(p1.distance(p1,p2), 18);
    }

    @Test
    public void testDistanceZerro() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        double d = Math.sqrt((0));
        Assert.assertEquals(p1.distance(p1,p2), d);
    }
}
