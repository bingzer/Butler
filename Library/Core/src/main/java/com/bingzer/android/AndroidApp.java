package com.bingzer.android;

import android.app.Application;
import android.content.Context;

public abstract class AndroidApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Res.setBaseContext(this);
    }

    /**
     * Returns the application name
     */
    protected abstract String getApplicationName();

}
