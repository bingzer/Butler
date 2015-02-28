package com.bingzer.android;

import android.test.AndroidTestCase;

import com.bingzer.android.Randomite;

public class RandomiteTest extends AndroidTestCase{

    public void test_unique(){
        assertNotSame(Randomite.unique(), Randomite.unique());
        assertNotSame(Randomite.unique(), Randomite.unique());
        assertNotSame(Randomite.unique(), Randomite.unique());
    }

    public void test_uniqueId(){
        assertNotSame(Randomite.uniqueId(), Randomite.uniqueId());
        assertNotSame(Randomite.uniqueId(), Randomite.uniqueId());
        assertNotSame(Randomite.uniqueId(), Randomite.uniqueId());
        assertNotSame(Randomite.uniqueId(), Randomite.uniqueId());
    }

    public void test_choose_varargs(){
        Integer chosen = Randomite.choose(1,2,3,4,5,6);
        assertTrue(chosen >= 1 || chosen <= 6);

        chosen = Randomite.choose();
        assertNull(chosen);
    }

    public void test_chooseFrom_int(){
        int chosen = Randomite.chooseFrom(1, 10);
        assertTrue(chosen >= 1 || chosen <= 10);
    }

    public void test_chooseFrom_float(){
        float chosen = Randomite.chooseFrom(1f, 1.5f);
        assertTrue(chosen >= 0.9f || chosen <= 1.5f);
    }

    public void test_chooseFrom_double(){
        double chosen = Randomite.chooseFrom(1d, 1.5d);
        assertTrue(chosen >= 1f || chosen <= 1.5f);
    }

}
