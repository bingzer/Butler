package com.bingzer.android;

import android.test.AndroidTestCase;

public class ResTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        Res.setBaseContext(getContext());
    }

    public void testGetContext() {
        assertTrue(Res.getContext() == getContext().getApplicationContext());
    }

    public void testGetScreenDimension() {
        assertTrue(Res.getScreenWidth() > 0);
        assertTrue(Res.getScreenHeight() > 0);
    }

}
