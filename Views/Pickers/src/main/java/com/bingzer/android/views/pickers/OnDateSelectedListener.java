package com.bingzer.android.views.pickers;

import java.util.Date;

public interface OnDateSelectedListener {

    /**
     * Invoked when user select a date/time
     * @param picker the picker
     * @param date the selected date
     */
    public void onDateSelected(DateTimePicker picker, Date date);
}
