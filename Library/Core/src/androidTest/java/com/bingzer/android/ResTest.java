package com.bingzer.android;

import android.test.AndroidTestCase;

/**
 * Manifest location:
 * androidTest/AndroidManifest.xml
 */
public class ResTest extends AndroidTestCase {

    public void testGetContext() {
        assertTrue(Res.getContext() == getContext().getApplicationContext());
    }

    public void testGetScreenDimension() {
        assertTrue(Res.getScreenWidth() > 0);
        assertTrue(Res.getScreenHeight() > 0);
    }

    public void testGetApplicationName(){
        assertEquals("ButlerAndroidApplicationTest", Res.getApplicationName());
    }

    public void testGetVersionCode(){
        assertEquals("99", Res.getVersionCode());
    }

    public void testGetVersionName(){
        assertEquals("ButlerVersion1.1", Res.getVersionName());
    }

    public void testIsApplicationDebuggable(){
        assertTrue(Res.isApplicationDebuggable());
    }

    public void testGetPackageName(){
        assertEquals("com.bingzer.android.test", Res.getPackageName());
    }

}
