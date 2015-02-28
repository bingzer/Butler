package com.bingzer.android;

import android.test.AndroidTestCase;

import java.util.Arrays;
import java.util.Collection;

import static com.bingzer.android.Collector.contains;
import static com.bingzer.android.Collector.size;
import static com.bingzer.android.Collector.toArray;

public class CollectorTest extends AndroidTestCase{
    public void test_size(){
        Iterable<Integer> iterable = Arrays.asList(1,2,3);
        assertEquals(3, size(iterable));
    }

    public void test_contains(){
        Iterable<Integer> iterable = Arrays.asList(1,2,3);
        assertTrue(contains(iterable, 3));
        assertFalse(contains(iterable, 4));
    }

    public void test_toArray(){
        Collection<Integer> collection = Arrays.asList(1,2,3);
        Integer[] array = toArray(Integer.class, collection);

        assertEquals(3, array.length);
        assertEquals(1, (int) array[0]);
        assertEquals(2, (int) array[1]);
        assertEquals(3, (int) array[2]);
    }

    public void test_toArray_Varargs(){
        Integer[] array = toArray(1,2,3,4,5);
        assertEquals(5, array.length);
        assertEquals(1, (int) array[0]);
        assertEquals(2, (int) array[1]);
        assertEquals(3, (int) array[2]);
        assertEquals(4, (int) array[3]);
        assertEquals(5, (int) array[4]);
    }

}
