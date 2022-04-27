package ru.stqa.jtl.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance1() {
        Point p1 = new Point(0, 1);
        Point p2 = new Point(0, 2);
        Assert.assertEquals(p1.distance(p2), 1.0);
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(0, 2);
        Point p2 = new Point(3, 2);
        Assert.assertEquals(p1.distance(p2), 3.0);
    }
}
