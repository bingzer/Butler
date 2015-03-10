package com.bingzer.android.patterns.uicommand.app;

import com.bingzer.android.AndroidApp;
import com.bingzer.android.patterns.UICommand;

public class UICommandApp extends AndroidApp implements UICommand.Listener {

    @Override
    public void onCreate() {
        super.onCreate();
        UICommand.addCommandListener(this);
    }

    @Override
    public void onTerminate() {
        UICommand.removeCommandListener(this);
        super.onTerminate();
    }

    @Override
    public void onCommandInvoked(int commandType, Object... args) {
        // placeholder
    }
}
