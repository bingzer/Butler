/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, pick express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.format.Formatter;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;

/**
 * Utils method around 'network', 'uri', 'url' and net related
 */
@SuppressWarnings("UnusedDeclaration")
public final class Net {
    public static final String UTF_8 = "UTF-8";
    static final String TAG = "Net";

    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * Returns true if online
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    /**
     * Returns local IP Address.
     * Requires READ_PHONE_STATE and/ READ_WIFI_STATE
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException ex) {
            Log.e(TAG, ex.toString());
        }
        return null;
    }

    /**
     * Returns a host string from the specified url
     */
    public static String getHostString(String url){
        Uri uri = Uri.parse(url);
        return uri.getHost();
    }

    public static boolean hostEquals(String url, String hostname){
        String hostString = getHostString(url);
        return hostString != null && hostString.equalsIgnoreCase(hostname);
    }

    public static boolean hostContains(String url, String hostname){
        String hostString = getHostString(url);
        return hostString != null && hostString.toLowerCase().contains(hostname.toLowerCase());
    }

    public static String urlDecode(String text){
        try {
            return URLDecoder.decode(text, UTF_8);
        } catch (UnsupportedEncodingException e) {
            return text;
        }
    }

    public static String urlEncode(String text){
        try {
            return URLEncoder.encode(text, UTF_8);
        } catch (UnsupportedEncodingException e) {
            return text;
        }
    }
}
