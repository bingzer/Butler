package com.bingzer.android;

import android.test.AndroidTestCase;

public class VarArgsTest extends AndroidTestCase {

    public void testAt(){
        testAt("Kool");
    }

    private void testAt(Object... args){
        assertEquals("Kool", VarArgs.at(0, args));
        assertNull(VarArgs.at(1, args));
    }

    ///////////////////////////////////////////////////////////////////////////////

    public void testStringAt(){
        testStringAt("Hello", "World");
    }

    private void testStringAt(String... args){
        assertEquals("Hello", VarArgs.at(0, args));
        assertEquals("World", VarArgs.at(1, args));
    }
}
