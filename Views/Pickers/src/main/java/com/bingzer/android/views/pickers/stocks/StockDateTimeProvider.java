package com.bingzer.android.views.pickers.stocks;

import android.content.Context;

import com.bingzer.android.views.pickers.BaseDateTimeProvider;
import com.bingzer.android.views.pickers.DateTimePicker;

import java.util.Date;

public class StockDateTimeProvider extends BaseDateTimeProvider {

    public StockDateTimeProvider(Context context){
        super(context);
    }

    @Override
    public DateTimePicker getDatePicker() {
        DatePicker datePicker = new DatePicker(getContext());
        datePicker.setDate(date);
        datePicker.setOnDateSelectedListener(this);
        return datePicker;
    }

    @Override
    public DateTimePicker getTimePicker() {
        TimePicker timePicker = new TimePicker(getContext());
        timePicker.setDate(date);
        timePicker.setOnDateSelectedListener(this);
        return timePicker;
    }

    @Override
    public void onDateSelected(DateTimePicker picker, Date date) {
        if(listener != null){
            listener.onDateSelected(picker, date);
        }
    }
}
