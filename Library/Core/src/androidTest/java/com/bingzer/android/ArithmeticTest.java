package com.bingzer.android;

import android.test.AndroidTestCase;

import static com.bingzer.android.Arithmetic.avg;
import static com.bingzer.android.Arithmetic.sum;

public class ArithmeticTest extends AndroidTestCase {

    public void test_sum_ints(){
        assertEquals(6, sum(1,2,3));
        assertEquals(0, sum());
    }

    public void test_avg_ints(){
        assertEquals(2, avg(1,2,3));
        assertEquals(0, avg());
    }

    public void test_sum_floats(){
        assertEquals(6, sum(1f,2f,3f), 0.01);
        assertEquals(0, (float) sum(), 0.01f);
    }

    public void test_avg_floats(){
        assertEquals(2, avg(1f,2f,3f), 0.01);
        assertEquals(0, (float) avg(), 0.01f);
    }

    public void test_sum_double(){
        assertEquals(6, sum(1d,2d,3d), 0.01);
        assertEquals(0, sum(), 0.01);
    }

    public void test_avg_double(){
        assertEquals(2, avg(1d,2d,3d), 0.01);
        assertEquals(0, avg(), 0.01);
    }
}
