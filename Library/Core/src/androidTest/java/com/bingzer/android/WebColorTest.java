package com.bingzer.android;

import android.test.AndroidTestCase;

import com.bingzer.android.WebColor;

import static com.bingzer.android.WebColor.getHexColorCode;

public class WebColorTest extends AndroidTestCase {

    /*

    public static final int BLACK       = 0xFF000000;
    public static final int DKGRAY      = 0xFF444444;
    public static final int GRAY        = 0xFF888888;
    public static final int LTGRAY      = 0xFFCCCCCC;
    public static final int WHITE       = 0xFFFFFFFF;
    public static final int RED         = 0xFFFF0000;
    public static final int GREEN       = 0xFF00FF00;
    public static final int BLUE        = 0xFF0000FF;
    public static final int YELLOW      = 0xFFFFFF00;
    public static final int CYAN        = 0xFF00FFFF;
    public static final int MAGENTA     = 0xFFFF00FF;
    public static final int TRANSPARENT = 0;


    public static final int WHITE       = android.graphics.Color.WHITE;
    // public static final int WHITE_SMOKE = 0xFFF5F5F5;
    public static final int RED         = android.graphics.Color.RED;
    public static final int PINK        = 0xFFFFC0CB;
    public static final int MAGENTA     = android.graphics.Color.MAGENTA;
    public static final int ORANGE      = 0xFFFF7F00;
    public static final int MAROON      = 0xFF800000;
    public static final int YELLOW      = android.graphics.Color.YELLOW;
    public static final int GREEN       = android.graphics.Color.GREEN;
    public static final int LIME        = 0xFFBFFF00;
    public static final int OLIVE       = 0xFF808000;
    public static final int BLUE        = android.graphics.Color.BLUE;
    public static final int AQUA        = android.graphics.Color.CYAN;
    public static final int TEAL        = 0xFF008080;
    public static final int NAVY_BLUE   = 0xFF000080;
    public static final int PURPLE      = 0xFF800080;
    public static final int BLACK       = android.graphics.Color.BLACK;
    public static final int BROWN       = 0xFF964B00;
    public static final int SILVER      = android.graphics.Color.LTGRAY;
    public static final int GRAY        = android.graphics.Color.GRAY;
    public static final int DARK_GRAY   = android.graphics.Color.DKGRAY;
     */

    public void test_getHexColorCode(){


        assertEquals("#FFFFFF", getHexColorCode(WebColor.WHITE));
        assertEquals("#FF0000", getHexColorCode(WebColor.RED));
        assertEquals("#FFC0CB", getHexColorCode(WebColor.PINK));
        assertEquals("#FF00FF", getHexColorCode(WebColor.MAGENTA));
        assertEquals("#FF7F00", getHexColorCode(WebColor.ORANGE));
        assertEquals("#800000", getHexColorCode(WebColor.MAROON));
        assertEquals("#FFFF00", getHexColorCode(WebColor.YELLOW));
    }
}
