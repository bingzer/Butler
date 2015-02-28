package com.bingzer.android;

import android.test.AndroidTestCase;

import com.bingzer.android.Timespan;

import java.util.Calendar;
import java.util.Date;

import static com.bingzer.android.Parser.parseBoolean;
import static com.bingzer.android.Parser.parseDate;
import static com.bingzer.android.Parser.parseDouble;
import static com.bingzer.android.Parser.parseFloat;
import static com.bingzer.android.Parser.parseInt;
import static com.bingzer.android.Parser.parseLong;

public class ParserTest extends AndroidTestCase{

    public void test_parseLong(){
        assertEquals(100l, parseLong("100", -1));
        assertEquals(10, parseLong("10", -1));

        assertEquals(-1, parseLong("ssfd", -1));
    }

    public void test_parseInt(){
        assertEquals(100, parseInt("100", -1));
        assertEquals(10, parseInt("10", -1));

        assertEquals(-1, parseInt("ssfd", -1));
    }

    public void test_parseFloat(){
        assertEquals(100, parseFloat("100", -1), 0.1f);
        assertEquals(10, parseFloat("10", -1), 0.1f);

        assertEquals(-1, parseFloat("ssfd", -1), 0.1f);
    }

    public void test_parseDouble(){
        assertEquals(100, parseDouble("100", -1), 0.1);
        assertEquals(10, parseDouble("10", -1), 0.1);

        assertEquals(-1, parseDouble("ssfd", -1), 0.1);
    }

    public void test_parseBoolean(){
        assertTrue(parseBoolean("true"));
        assertTrue(parseBoolean("True"));
        assertFalse(parseBoolean("truex"));
        assertFalse(parseBoolean("false"));
        assertFalse(parseBoolean("False"));
    }

    public void test_parseBoolean_TrueValue(){
        assertTrue(parseBoolean("true", "true"));
        assertTrue(parseBoolean("truex", "truex"));
        assertTrue(parseBoolean("false", "false"));
        assertTrue(parseBoolean("False", "false"));
        assertTrue(parseBoolean("Yes", "yes"));

        assertFalse(parseBoolean("True", "XX"));
        assertFalse(parseBoolean("True", "Yes"));
    }

    public void test_parseDate(){
        Date date = parseDate("02/05/2014", "MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(2014, Calendar.FEBRUARY, 5);

        assertTrue(Math.abs(cal.getTime().getTime() - date.getTime()) < Timespan.DAYS_1);
    }
}
