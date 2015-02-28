package com.bingzer.android;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import static com.bingzer.android.Stringify.ellipsize;
import static com.bingzer.android.Stringify.emptyIfNull;
import static com.bingzer.android.Stringify.fastSplit;
import static com.bingzer.android.Stringify.isNullOrEmpty;
import static com.bingzer.android.Stringify.join;

public class StringifyTest extends AndroidTestCase {

    public void test_emptyIfNull(){
        assertEquals("", emptyIfNull(null));

        assertEquals("any", emptyIfNull("any"));
    }

    public void test_isNullOrEmpty(){
        assertEquals(true, isNullOrEmpty(null));
        assertEquals(true, isNullOrEmpty(""));
        assertEquals(false, isNullOrEmpty("t"));
    }

    public void test_join(){
        assertEquals("", join(",").toString());
        assertEquals("1,2,3", join(",", "1", "2", "3").toString());
        assertEquals("3", join(",", "3").toString());

        assertEquals("5:4:3", join(":", "5","4","3").toString());
    }

    public void test_fastSplit(){
        List<String> list = new ArrayList<String>();
        fastSplit(list, "hello,world", ",");

        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));

        /////////////////////////////////////////////////////
        list.clear();
        fastSplit(list, "hello:world", ":");

        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));

        /////////////////////////////////////////////////////
        list.clear();
        fastSplit(list, "hello:world:", ":");

        assertEquals(2, list.size());
        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));
    }

    public void test_fastSplit_StringBuilder(){
        List<String> list = new ArrayList<String>();
        fastSplit(list, new StringBuilder("hello").append(",").append("world"), ",");

        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));

        /////////////////////////////////////////////////////
        list.clear();
        fastSplit(list, new StringBuilder("hello").append(":").append("world"), ":");

        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));

        /////////////////////////////////////////////////////
        list.clear();
        fastSplit(list, new StringBuilder("hello").append(":").append("world").append(":"), ":");

        assertEquals(2, list.size());
        assertEquals("hello", list.get(0));
        assertEquals("world", list.get(1));
    }

    public void test_ellipsize(){
        String text = "1234567890";

        String actual = ellipsize(text, 5).toString();
        assertEquals("12...", actual);
    }

    public void test_ellipsize_CharSequence_Int(){
        String text = "1234567890";

        String expected = "12...";
        String actual = Stringify.ellipsize(text, 5).toString();
        assertEquals(expected, actual);

        text = "lower";
        expected = "l";
        actual = Stringify.ellipsize(text, 1).toString();
        assertEquals(expected, actual);

        text = null;
        expected = null;
        assertEquals(expected, Stringify.ellipsize(text, 10));

        text = "1234567890";
        expected = "12abc";
        actual = Stringify.ellipsize(text, 5, "abc").toString();
        assertEquals(expected, actual);
    }
}
