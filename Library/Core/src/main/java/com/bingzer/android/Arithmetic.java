package com.bingzer.android;

/**
 * Math and all that
 */
public final class Arithmetic {

    /**
     * Total
     */
    public static float sum(float... numbers){
        if(numbers == null) throw new IllegalArgumentException("param numbers is null");

        float f = 0;
        for(float fNum : numbers)
            f += fNum;
        return f;
    }

    /**
     * Average
     */
    public static float avg(float... numbers){
        if(numbers == null) throw new IllegalArgumentException("param numbers is null");
        if(numbers.length == 0) return 0;
        float sum = sum(numbers);
        return sum / numbers.length;
    }

    /**
     * Total
     */
    public static int sum(int... numbers){
        if(numbers == null) throw new IllegalArgumentException("param numbers is null");

        int f = 0;
        for(int fNum : numbers)
            f += fNum;
        return f;
    }

    /**
     * Average
     */
    public static int avg(int... numbers){
        if(numbers == null) throw new IllegalArgumentException("param numbers is null");
        if(numbers.length == 0) return 0;
        float sum = sum(numbers);
        return (int) (sum / numbers.length);
    }

    /**
     * Returns total
     */
    public static double sum(double... numbers){
        if(numbers == null) throw new IllegalArgumentException("param numbers is null");

        double f = 0;
        for(double fNum : numbers)
            f += fNum;
        return f;
    }

    public static double avg(double... numbers){
        if(numbers == null) throw new IllegalArgumentException("param numbers is null");
        if(numbers.length == 0) return 0;
        double sum = sum(numbers);
        return sum / numbers.length;
    }


    //////////////////////////////////////////////////////////////////////////

    private Arithmetic(){
        // nothing
    }
}
