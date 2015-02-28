package com.bingzer.android;

import android.test.AndroidTestCase;

public class DimensionTest extends AndroidTestCase {
    public void test_cotr(){
        Dimension dim = new Dimension();
        assertEquals(0, dim.width);
        assertEquals(0, dim.height);

        dim = new Dimension(1,1);
        assertEquals(1, dim.width);
        assertEquals(1, dim.height);
    }
}
