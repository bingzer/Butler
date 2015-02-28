package com.bingzer.android;

import android.test.AndroidTestCase;

public class PointTest extends AndroidTestCase {

    public void testXY_Initial(){
        Point point = new Point();

        assertEquals(0f, point.x);
        assertEquals(0f, point.y);
    }

    public void testXY(){
        Point point = new Point();

        point.x = 1;
        point.y = 2;

        assertEquals(1f, point.x);
        assertEquals(1f, point.getX());
        assertEquals(2f, point.y);
        assertEquals(2f, point.getY());
    }

    public void testToString(){
        Point p = new Point(1, 2);

        assertEquals("1.0,2.0", p.toString());
    }
}
