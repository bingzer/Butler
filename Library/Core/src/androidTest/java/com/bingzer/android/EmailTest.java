package com.bingzer.android;

import android.test.AndroidTestCase;

import static com.bingzer.android.Email.validate;

public class EmailTest extends AndroidTestCase {

    public void test_validate_ok(){
        assertTrue(validate("ricky@test-case.com"));
        assertTrue(validate("ricky@123-case.com"));
        assertTrue(validate("ricky@123_underscore.net.com.org"));
    }

    public void test_validate_bad(){
        assertFalse(validate("@test-case.com"));
        assertFalse(validate("123-case.com"));
        assertFalse(validate("ricky"));
    }
}
