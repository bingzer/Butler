package com.bingzer.android.views.pickers.stocks;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;

import com.bingzer.android.views.pickers.BaseDateTimePicker;

import java.util.Calendar;

class TimePicker extends BaseDateTimePicker implements TimePickerDialog.OnTimeSetListener {

    private TimePickerDialog dialog;

    public TimePicker(Context context) {
        super(context);
    }

    /**
     * Returns either PICKER_DATE, PICKER_TIME or PICKER_DATETIME constants
     */
    @Override
    public int getSupportedPickerType() {
        return PICKER_TIME;
    }

    /**
     * Shows the picker
     */
    @Override
    public void show() {
        if(dialog == null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
            int minutes = cal.get(Calendar.MINUTE);
            boolean is24 = DateFormat.is24HourFormat(getContext());

            dialog = new TimePickerDialog(getContext(), this, hourOfDay, minutes, is24);
            dialog.show();
        }
    }

    /**
     * Closes
     */
    @Override
    public void dismiss() {
        if(dialog != null)
            dialog.dismiss();
    }

    /**
     * @param view      The view associated with this listener.
     * @param hourOfDay The hour that was set.
     * @param minute    The minute that was set.
     */
    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        notifyDateSet(cal.getTime());
    }
}
