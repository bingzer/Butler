package com.bingzer.android;

import android.test.AndroidTestCase;

import com.bingzer.android.Constants;

public class ConstantsTest extends AndroidTestCase {
    public void test_allConstants(){
        assertEquals("", Constants.Empty);
        assertEquals(".", Constants.Dot);
        assertEquals(",", Constants.Comma);
        assertEquals(":", Constants.Colon);
        assertEquals(";", Constants.SemiColon);
    }
}
