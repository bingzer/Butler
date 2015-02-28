package com.bingzer.android.patterns;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class UICommand {
    private static final String TAG = "UICommand";
    private static final Map<String, Listener> listenerList = new ConcurrentHashMap<>();

    private UICommand(){
        // nothing
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public static synchronized void addCommandListener(UICommand.Listener listener){
        if(listener == null) return;
        try {
            Log.i(TAG, "Adding CommandListener " + listener);
            listenerList.put(listener.getClass().getName(), listener);
        }
        catch (UnsupportedOperationException e){
            // stupid editor. safely ignore
        }
    }

    public static synchronized void removeCommandListener(UICommand.Listener listener){
        if(listener == null) return;
        try {
            Log.i(TAG, "Removing CommandListener " + listener);
            listenerList.remove(listener.getClass().getName());
        }
        catch (UnsupportedOperationException e){
            // stupid editor. safely ignore
        }
    }

    public static synchronized void invokeDelayArgs(int delay, final int commandType, final Object... args){
        Log.v(TAG, "invokeDelayArgs(delay=" + delay + ", commandType=" + commandType + ") with args");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override public void run() {
                invokeArgs(commandType, args);
            }
        }, delay);
    }

    public static synchronized void invokeArgs(final int commandType, final Object... args){
        Log.v(TAG, "invokeArgs(commandType=" + commandType + ") with args");
        final Collection<Listener> listeners = listenerList.values();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override public void run() {
                for(final Listener listener : listeners){
                    listener.onCommandInvoked(commandType, args);
                }
            }
        });
    }

    public static synchronized void invokeDelay(int delay, final int... commandTypes){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override public void run() {
                invoke(commandTypes);
            }
        }, delay);
    }

    public static synchronized void invoke(int... commandTypes){
        for(int commandType : commandTypes){
            invokeArgs(commandType);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * The listener
     */
    public static interface Listener {

        /**
         * Invoked when receive a command
         * @param commandType the command
         * @param args argument that comes with it
         */
        void onCommandInvoked(int commandType, Object... args);

    }
}
