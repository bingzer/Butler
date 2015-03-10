package com.bingzer.android;

/**
 * Helper with dealing with var args.
 */
public final class VarArgs {

    @SuppressWarnings("unchecked")
    public static <T> T at(int index, Object... args){
        if (index < args.length)
            return (T) args[index];
        return null;
    }

    public static String stringAt(int index, Object... args){
        return at(index, args);
    }

    public static int integerAt(int index, Object... args){
        return at(index, args);
    }

    public static float floatAt(int index, Object... args){
        return at(index, args);
    }

    public static double doubleAt(int index, Object... args){
        return at(index, args);
    }

    public static boolean booleanAt(int index, Object... args){
        return at(index, args);
    }

    public static boolean chartAt(int index, Object... args){
        return at(index, args);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    private VarArgs(){
        // nothing
    }
}
