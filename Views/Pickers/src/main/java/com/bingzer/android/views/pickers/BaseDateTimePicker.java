package com.bingzer.android.views.pickers;

import android.content.Context;

import com.bingzer.android.Timespan;

import java.util.Date;

public abstract class BaseDateTimePicker implements DateTimePicker {

    private final Context context;
    protected Date date;
    protected OnDateSelectedListener listener;

    public BaseDateTimePicker(Context context){
        this.context = context;
        this.date = new Date(Timespan.now());
    }

    @Override
    public Context getContext() {
        return context;
    }

    /**
     * Sets the date
     */
    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns current date.
     */
    @Override
    public Date getDate() {
        return date;
    }

    /**
     * Sets OnDateSelectedListener
     */
    @Override
    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        this.listener = listener;
    }

    /**
     * Returns the listener
     */
    @Override
    public OnDateSelectedListener getOnDateSelectedListener() {
        return listener;
    }

    protected void notifyDateSet(Date date){
        if(listener != null){
            listener.onDateSelected(this, date);
        }
    }

}
