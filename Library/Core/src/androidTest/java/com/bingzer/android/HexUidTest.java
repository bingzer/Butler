package com.bingzer.android;

import android.test.AndroidTestCase;

public class HexUidTest extends AndroidTestCase {

    public void testRandomness(){
        for(int i = 0; i < 100; i++){
            assertNotSame(HexUidGenerator.generateUniqueId(), HexUidGenerator.generateUniqueId());
        }
    }
}
