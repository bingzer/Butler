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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.net.URISyntaxException;

/**
 * Commonly-used Intents
 *
 * @author Ricky Tobing
 *
 */
@SuppressWarnings("UnusedDeclaration")
@SuppressLint("DefaultLocale")
public final class GlobalIntents {

    private static final String TAG = "GlobalIntents";

    /**
     * Share url
     */
    public static void shareUrl(Context context, String url){
        try{
            Intent intent = Intent.parseUri(url, Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        catch (URISyntaxException e){
            Log.e(TAG, "shareUrl()", e);
        }
    }

    /**
     * Share url with chooser
     */
    public static void shareUrlWithChooser(Context context, String chooserTitle, String url){
        try{
            Intent intent = Intent.parseUri(url, Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(intent, chooserTitle));
        }
        catch (URISyntaxException e){
            Log.e(TAG, "shareUrlWithChooser()", e);
        }
    }

    /**
     * Send email
     *
     */
    public static void sendEmail(Context context, String subject, String body, String... recipients){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(intent);
    }

    /**
     * Send email with chooser
     */
    public static void sendEmailWithChooser(Context context, String chooserTitle, String subject, String body, String... recipients){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(intent, chooserTitle));
    }

    /**
     * Shows uninstall intent
     */
    public static void showUninstallIntent(Context context, String packageName) {
        Uri packageUri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, packageUri);
        context.startActivity(intent);
    }

    /**
     * Select image for a result
     */
    public static void selectImageForResult(Activity activity, int requestCode){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Shows market detail.
     * This uses {@link com.bingzer.android.BundleKeys#META_KEY_APPSTORE}
     */
    @SuppressLint("DefaultLocale")
    public static void openMarketDetail(String packageName){
        try{
            // switch between market..
            // default is google..
            String appStoreName = String.valueOf(Res.getApplicationMetaValue(packageName, BundleKeys.META_KEY_APPSTORE));

            // ------ google..
            if(appStoreName.toLowerCase().equals("amazon")){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + packageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Res.getContext().startActivity(intent);
            }
            // ------ other..
            else{
                try{
                    // -- try google
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Res.getContext().startActivity(intent);
                }
                catch (Throwable e){
                    // -- (just http://) intent
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Res.getContext().startActivity(intent);
                }
            }
        }
        catch (Throwable e){
            throw new RuntimeException(e);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    private GlobalIntents(){
        // nothing
    }
}
