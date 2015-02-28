package com.bingzer.android.views.pickers;

import android.content.Context;

import java.util.Date;

public interface DateTimeProvider extends OnDateSelectedListener {

    /**
     * Context!
     */
    Context getContext();

    /**
     * Date Picker
     */
    DateTimePicker getDatePicker();

    /**
     * Time picker
     */
    DateTimePicker getTimePicker();

    /**
     * Sets the date
     */
    void setDate(Date date);

    /**
     * Returns current date.
     */
    Date getDate();

    /**
     * Sets OnDateSelectedListener
     */
    void setOnDateSelectedListener(OnDateSelectedListener listener);

    /**
     * Returns the listener
     */
    OnDateSelectedListener getOnDateSelectedListener();

}
