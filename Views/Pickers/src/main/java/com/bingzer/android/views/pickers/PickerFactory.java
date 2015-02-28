package com.bingzer.android.views.pickers;

import android.content.Context;

import com.bingzer.android.views.pickers.stocks.StockDateTimeProvider;

public final class PickerFactory {

    public static DateTimeProvider createDateTimeFactory(Context context, OnDateSelectedListener listener){
        DateTimeProvider provider = new StockDateTimeProvider(context);
        provider.setOnDateSelectedListener(listener);
        return provider;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    private PickerFactory(){
        // nothing
    }

}
