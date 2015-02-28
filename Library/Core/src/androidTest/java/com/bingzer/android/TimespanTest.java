package com.bingzer.android;

import android.test.AndroidTestCase;

import com.bingzer.android.Timespan;

public class TimespanTest extends AndroidTestCase {

    public void test_AllConstants(){
        assertEquals(-1, Timespan.NEVER);
        assertEquals(1000, Timespan.SECONDS_1);
        assertEquals(1000*15, Timespan.SECONDS_15);
        assertEquals(1000*30, Timespan.SECONDS_30);
        assertEquals(1000*60, Timespan.MINUTES_1);
        assertEquals(1000*60*10, Timespan.MINUTES_10);
        assertEquals(1000*60*30, Timespan.MINUTES_30);
        assertEquals(1000*60*60, Timespan.HOURS_1);
        assertEquals(1000*60*60*2, Timespan.HOURS_2);
        assertEquals(1000*60*60*3, Timespan.HOURS_3);
        assertEquals(1000*60*60*4, Timespan.HOURS_4);
        assertEquals(1000*60*60*5, Timespan.HOURS_5);
        assertEquals(1000*60*60*6, Timespan.HOURS_6);
        assertEquals(1000*60*60*7, Timespan.HOURS_7);
        assertEquals(1000*60*60*8, Timespan.HOURS_8);
        assertEquals(1000*60*60*9, Timespan.HOURS_9);
        assertEquals(1000*60*60*12, Timespan.HOURS_12);
        assertEquals(1000*60*60*24, Timespan.DAYS_1);
        assertEquals(1000*60*60*24*2, Timespan.DAYS_2);
        assertEquals(1000*60*60*24*7, Timespan.WEEKS_1);
        assertEquals(1000*60*60*24*7*2, Timespan.WEEKS_2);
        assertEquals((long)2.62974e9, Timespan.MONTHS_1);
        assertEquals((long)5.25949e+9, Timespan.MONTHS_2);
        assertEquals((long)3.15569e+10, Timespan.YEARS_1);
    }

    public void test_now(){
        long now = Timespan.now();
        long now2 = System.currentTimeMillis();
        assertTrue(Math.abs(now - now2) < Timespan.MINUTES_1);
    }

    public void test_today(){
        long now = Timespan.today().getTime();
        long now2 = System.currentTimeMillis();
        assertTrue(Math.abs(now - now2) < Timespan.MINUTES_1);
    }

}
