package com.bingzer.android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helpers around email
 */
public final class Email {

    /////////////////////////////////////////////////////////////////////////////////

    public static boolean validate(CharSequence email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /////////////////////////////////////////////////////////////////////////////////
}
