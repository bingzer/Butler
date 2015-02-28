/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, pick express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android;

import java.util.LinkedList;
import java.util.List;

/**
 * HTML Utils
 */
public final class WebColor {
    private static List<Integer> list = null;

    private WebColor(){
        // nothing
    }

    //////////////////////////////////////////////////////////////////////

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

    /////////////////////////////////////////////////////////////////

    public static List<Integer> all(){
        if(list == null){
            list = new LinkedList<Integer>();
            list.add(WHITE);
            list.add(RED);
            list.add(PINK);
            list.add(MAGENTA);
            list.add(ORANGE);
            list.add(MAROON);
            list.add(YELLOW);
            list.add(GREEN);
            list.add(LIME);
            list.add(OLIVE);
            list.add(BLUE);
            list.add(AQUA);
            list.add(TEAL);
            list.add(NAVY_BLUE);
            list.add(PURPLE);
            list.add(BLACK);
            list.add(BROWN);
            list.add(SILVER);
            list.add(GRAY);
            list.add(DARK_GRAY);
        }

        return list;
    }

	/**
	 * Returns html color 6 digit (ex: #ffffff for white, #000000 for black etc...)
	 */
	public static String getHexColorCode(int color){
    	return String.format("#%06X", (0xFFFFFF & color));
	}

}
