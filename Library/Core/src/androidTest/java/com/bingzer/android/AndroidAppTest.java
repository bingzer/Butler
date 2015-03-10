package com.bingzer.android;

import android.test.ApplicationTestCase;

public class AndroidAppTest extends ApplicationTestCase<TestApp> {

    public AndroidAppTest() {
        super(TestApp.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }

    public void testResContextIsNotNull(){
        assertNotNull(Res.getContext());
    }

    public void testGetApplicationName(){
        assertEquals("ButlerAndroidApplicationTest", getApplication().getApplicationName());
    }
}
