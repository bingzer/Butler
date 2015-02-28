package com.bingzer.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.test.AndroidTestCase;

import java.net.SocketException;

import static com.bingzer.android.Net.getHostString;
import static com.bingzer.android.Net.hostEquals;
import static com.bingzer.android.Net.isOnline;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetTest extends AndroidTestCase {


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Explicitly set the Dexmaker cache, so tests that use mockito work
        final String dexCache = getContext().getCacheDir().getPath();
        System.setProperty("dexmaker.dexcache", dexCache);
    }

    public void test_isOnline(){
        Context context = mock(Context.class);
        ConnectivityManager mgr = mock(ConnectivityManager.class);
        NetworkInfo info = mock(NetworkInfo.class);

        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mgr);
        when(mgr.getActiveNetworkInfo()).thenReturn(info);
        when(info.isConnected()).thenReturn(true);

        assertTrue(isOnline(context));

        when(info.isConnected()).thenReturn(false);

        assertFalse(isOnline(context));
    }

    public void test_getLocalIpAddress() throws SocketException {
        //assertEquals("host-address", Net.getLocalIpAddress());
    }

    public void test_getHostString(){
        assertEquals("google.com", getHostString("http://google.com"));
        assertEquals("localhost", getHostString("http://localhost"));
    }

    public void test_hostEquals(){
        assertTrue(hostEquals("http://google.com", "google.com"));
        assertTrue(hostEquals("http://localhost", "localhost"));
    }
}
