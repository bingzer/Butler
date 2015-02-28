package com.bingzer.android;

import java.util.Collection;
import java.util.UUID;

/**
 * Random helpers
 */
public final class Randomite {

    /**
     * Returns random UUID
     */
    public static String unique(){
        return UUID.randomUUID().toString();
    }

    public static long uniqueId(){
        UUID uuid = UUID.randomUUID();
        return uuid.getMostSignificantBits();
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * Returns a random value from
     * collection <code>any</code>
     */
    public static <T> T choose(Class<T> clazz, Collection<T> any){
        return choose(Collector.toArray(clazz, any));
    }

    /**
     * Gets random value from param <code>any</code>
     */
    @SafeVarargs
    public static <T> T choose(T... any){
        if(any == null) throw new NullPointerException("any is null");
        if(any.length == 0) return null;

        return any[(int) (Math.random() * any.length)];
    }

    /**
     * Returns random from to to
     */
    public static double chooseFrom(double from, double to){
        return from + (int) (Math.random() * ((to - from) + 1));
    }

    /**
     * Returns random from to to
     */
    public static float chooseFrom(float from, float to){
        return from + (int) (Math.random() * ((to - from) + 1));
    }

    public static int chooseFrom(int from, int to){
        return from + (int) (Math.random() * ((to - from) + 1));
    }

    public static boolean decide(){
        return decide(0.5f);
    }

    public static boolean decide(float bias){
        return Math.random() < bias;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    private Randomite(){
        //
    }
}
