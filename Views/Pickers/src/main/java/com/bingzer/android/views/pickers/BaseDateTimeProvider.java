package com.bingzer.android.views.pickers;

import android.content.Context;

import com.bingzer.android.Timespan;

import java.util.Date;

public abstract class BaseDateTimeProvider implements DateTimeProvider {

    private final Context context;
    protected Date date;
    protected OnDateSelectedListener listener;

    public BaseDateTimeProvider(Context context){
        this.context = context;
        this.date = new Date(Timespan.now());
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public OnDateSelectedListener getOnDateSelectedListener() {
        return listener;
    }
}
