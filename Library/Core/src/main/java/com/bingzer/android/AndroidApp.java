/**
 * Copyright 2015 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android;

import android.app.Application;
import android.util.Log;

/**
 * Extending this will set Res.setBaseContext() automatically.
 */
public class AndroidApp extends Application {

    protected String name;

    @Override
    public void onCreate() {
        super.onCreate();
        Res.setBaseContext(this);

        name = Res.getApplicationName();
        Log.i(name, "Starting " + name);
        Log.i(name, "Package Name: " + Res.getPackageName());
        Log.i(name, "Version Name: " + Res.getVersionName());
        Log.i(name, "Version Code: " + Res.getVersionCode());
        Log.i(name, "Debuggable  : " + isDebuggable());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.i(name, "onTerminate()");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public final String getApplicationName(){
        return name;
    }

    public final boolean isDebuggable(){
        return Res.isApplicationDebuggable();
    }
}
