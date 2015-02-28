package com.bingzer.android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Parser {

    public static long parseLong(String input, long defaultValue){
        try{
            return Long.parseLong(input);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static int parseInt(String input, int defaultValue){
        try{
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static float parseFloat(CharSequence input, float defaultValue){
        return parseFloat(input.toString(), defaultValue);
    }

    public static float parseFloat(String input, float defaultValue){
        try{
            return Float.parseFloat(input);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    public static double parseDouble(String input, double defaultValue){
        try{
            return Double.parseDouble(input);
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }

    /**
     * Parse boolean if input is the same as trueValue it will return true
     */
    public static boolean parseBoolean(String input, String trueValue){
        return !(input == null || trueValue == null) && input.equalsIgnoreCase(trueValue);
    }

    public static boolean parseBoolean(String input){
        return Boolean.parseBoolean(input);
    }

    public static Date parseDate(String input, String pattern){
        try{
            return new SimpleDateFormat(pattern).parse(input);
        }
        catch (ParseException e){
            return null;
        }
    }

    /////////////////////////////////////////////////////////
    private Parser(){
        // nothing
    }
}