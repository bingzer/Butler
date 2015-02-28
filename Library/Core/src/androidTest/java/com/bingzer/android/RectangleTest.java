package com.bingzer.android;

import android.test.AndroidTestCase;

public class RectangleTest extends AndroidTestCase {

    public void testEquals(){
        Rectangle r1 = new Rectangle(1,1,1,1);
        Rectangle r2 = new Rectangle(1,1,1,2);
        Rectangle r11 = new Rectangle(1,1,1,1);

        assertNotSame(r1, r2);
        assertEquals(r1, r11);
    }
}
