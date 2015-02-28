package com.bingzer.android.views.pickers;

import android.content.Context;

public interface Picker {

    Context getContext();

    int getSupportedPickerType();

    /**
     * Shows the picker
     */
    void show();

    /**
     * Closes
     */
    void dismiss();

}
