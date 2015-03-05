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

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import com.bingzer.android.Dimension;

@SuppressWarnings("UnusedDeclaration")
public final class ViewUtils {

	/**
	 * Returns the current view location on screen
	 */
	public static Dimension getLocationOnScreen(View view){
		if(view == null) return null;
		
		int[] location = new int[2];
		location[0] = 0;
		location[1] = 0;
		
		view.getLocationOnScreen(location);
		return new Dimension(location[0], location[1]);
	}

    /**
     * This method converts device specific pixels to device independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent db equivalent to px value
     */
    public static float convertPixelsToDp(Context context, float px) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);

    }

    /**
     * Convert dp to pixels (int)
     */
    public static int convertDpToPixelInt(Context context, float dp) {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (dp * (metrics.densityDpi / 160f));
    }

    /**
     * Convert dp to pixels (float)
     */
    public static float convertDpToPixel(Context context, float dp) {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (dp * (metrics.densityDpi / 160f));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    private ViewUtils(){
        // nothing
    }
}
