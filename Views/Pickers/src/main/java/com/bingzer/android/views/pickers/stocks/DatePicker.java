package com.bingzer.android.views.pickers.stocks;

import android.app.DatePickerDialog;
import android.content.Context;

import com.bingzer.android.views.pickers.BaseDateTimePicker;

import java.util.Calendar;

class DatePicker extends BaseDateTimePicker implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog dialog;

    DatePicker(Context context){
        super(context);
    }

    /**
     * Returns either PICKER_DATE, PICKER_TIME or PICKER_DATETIME constants
     */
    @Override
    public int getSupportedPickerType() {
        return PICKER_DATE;
    }

    /**
     * Shows the picker
     */
    @Override
    public void show() {
        if(dialog == null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            dialog = new DatePickerDialog(getContext(), this, year, month, day);
            dialog.show();
        }
    }

    /**
     * Closes
     */
    @Override
    public void dismiss() {
        if(dialog != null) dialog.dismiss();
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(year, monthOfYear, dayOfMonth);

        notifyDateSet(cal.getTime());
    }
}
