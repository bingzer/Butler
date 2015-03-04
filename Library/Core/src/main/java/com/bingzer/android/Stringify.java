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

import com.bingzer.android.async.Delegate;
import com.bingzer.android.async.Task;

import java.io.IOException;
import java.util.List;

import static com.bingzer.android.async.Async.doAsync;

/**
 * Utility related to string
 */
@SuppressWarnings("unused")
public final class Stringify {
    //////////////////////////////////////////////////////////////////////

    private Stringify(){
        // nothing
    }

    //////////////////////////////////////////////////////////////////////

    public static String emptyIfNull(String any){
        if (any == null) return "";
        return any;
    }

    /**
     * Returns an empty string if <code>any</code> is null.
     * Otherwise returns <code>any</code>
     */
    public static CharSequence emptyIfNull(CharSequence any){
        if (any == null) return "";
        return any;
    }

    /**
     * CHeck to see if <code>any</code> is pick
     * null or empty
     */
    public static boolean isNullOrEmpty(CharSequence any){
        return (any == null || any.length() == 0);
    }

    /**
     * Joins string
     */
    public static CharSequence join(String separator, String... strings){
        StringBuilder builder = new StringBuilder();
        if(strings != null){
            for(int i = 0; i < strings.length; i++){
                builder.append(strings[i]);
                // append the separator if it's not the last one
                if(i != strings.length - 1) builder.append(separator);
            }
        }
        return builder;
    }

    /**
     * Split a string based on the delimiter and returns a list of string
     */
    public static void fastSplit(List<String> list, CharSequence target, String delimiter){
        if (target instanceof StringBuilder)
            fastSplit(list, ((StringBuilder) target), delimiter);
        else
            fastSplit(list, new StringBuilder().append(target), delimiter);
    }

    public static void fastSplitAsync(final List<String> list, final CharSequence target, final String delimiter, Task<Void> task){
        doAsync(task, new Delegate<Void>() {
            @Override
            public Void invoke() {
                fastSplit(list, target, delimiter);
                return null;
            }
        });
    }

    /**
     * Split a string based on the delimiter and returns a list of string
     */
    public static void fastSplit(List<String> list, StringBuilder target, String delimiter){
        int start = 0;
        int end   = target.indexOf(delimiter);
        while(end >= 0){
            list.add(target.substring(start, end));
            start = end + 1;
            end   = target.indexOf(delimiter, start);
        }

        if(start < target.length() - 1)
            list.add(target.substring(start));
    }

    public static void fastSplitAsync(final List<String> list, final StringBuilder target, final String delimiter, Task<Void> task){
        doAsync(task, new Delegate<Void>() {
            @Override
            public Void invoke() {
                fastSplit(list, target, delimiter);
                return null;
            }
        });
    }


    //////////////////////////////////////////////////////////////////////

    public static CharSequence ellipsize(CharSequence text, int maximumLength){
        return ellipsize(text, maximumLength, "...");
    }

    public static CharSequence ellipsize(CharSequence text, int maximumLength, CharSequence ellipsizeString){
        if(Stringify.isNullOrEmpty(text)) return text;
        if(Stringify.isNullOrEmpty(ellipsizeString)) return text;

        final StringBuilder result = new StringBuilder();

        if(maximumLength > ellipsizeString.length() &&
                text.length() > maximumLength - ellipsizeString.length()){
            // subtract by the length of ellipsizeString
            result.append(text.subSequence(0, maximumLength - ellipsizeString.length()));
            result.append(ellipsizeString);
        }
        // if maximum length is actually less than the ellipsize char length
        // then, return text.sub(0, maximum length
        else if(maximumLength < ellipsizeString.length() &&
                text.length() > maximumLength){
            result.append(text.subSequence(0, maximumLength));
        }
        else{
            // otherwise return everything
            result.append(text);
        }

        return result;
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * Converts value to base64.
     * Returns it as encoded string in base64 format.
     * Do not use this for a really large string. Use
     * Base64.encode() instead
     */
    public static CharSequence toBase64(String value){
        return toBase64(value.getBytes());
    }

    public static void toBase64Async(final String value, Task<CharSequence> task){
        doAsync(task, new Delegate<CharSequence>() {
            @Override
            public CharSequence invoke() {
                return toBase64(value);
            }
        });
    }

    /**
     * Converts bytes to base64.
     * Returns it as encoded string in base64 format.
     * Do not use this for a really large string. Use
     * Base64.encode() instead
     */
    public static CharSequence toBase64(byte[] bytes){
        return new String(android.util.Base64.encode(bytes, android.util.Base64.DEFAULT));
    }

    public static void toBase64Async(final byte[] bytes, Task<CharSequence> task){
        doAsync(task, new Delegate<CharSequence>() {
            @Override
            public CharSequence invoke() {
                return toBase64(bytes);
            }
        });
    }

    /**
     * Returns the string value from base64encoded
     */
    public static CharSequence toString(String base64Encoded) throws IOException {
        return new String(toBytes(base64Encoded));
    }

    public static void toStringAsync(final String base64Encoded, Task<CharSequence> task){
        doAsync(task, new Delegate<CharSequence>() {
            @Override
            public CharSequence invoke() {
                try {
                    return Stringify.toString(base64Encoded);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Returns the decoded bytes from the base64encoded
     * @throws java.io.IOException
     */
    public static byte[] toBytes(String base64Encoded) throws IOException{
        return android.util.Base64.decode(base64Encoded, android.util.Base64.DEFAULT);
    }

    public static void toBytesAsync(final String base64Encoded, Task<byte[]> task){
        doAsync(task, new Delegate<byte[]>() {
            @Override
            public byte[] invoke() {
                try {
                    return Stringify.toBytes(base64Encoded);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}