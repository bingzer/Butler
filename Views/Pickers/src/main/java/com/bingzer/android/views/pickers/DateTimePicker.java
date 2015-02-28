package com.bingzer.android.views.pickers;

import java.util.Date;

public interface DateTimePicker extends Picker {

    int PICKER_DATE = 0;
    int PICKER_TIME = 1;
    int PICKER_DATETIME = 2;

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
