package com.bingzer.android;

/**
 * Helpers around Threads
 */
public final class Threading {

    public static void safeSleep(long millis){
        try{
            Thread.sleep(millis);
        }
        catch (InterruptedException e){
            // ignore..
        }
    }

    //////////////////////////////////////////////////////////////////////

    private Threading(){
        //
    }
}
