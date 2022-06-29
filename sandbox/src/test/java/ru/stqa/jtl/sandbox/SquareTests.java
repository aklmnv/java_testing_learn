package ru.stqa.jtl.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testArea(){
        Square s = new Square(6);
        Assert.assertEquals(s.area(), 25.0);
    }
}
