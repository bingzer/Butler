/**
 * Copyright 2014 Ricky Tobing
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

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Instance access to resources.
 * All resources should use ApplicationContext
 */
@SuppressWarnings("UnusedDeclaration")
public final class Res {

    private static Context context;

    /**
     * Sets the application context
     * @param context the context
     */
    protected static void setBaseContext(Context context){
        if (context == null)
            throw new RuntimeException("context == null");
        Res.context = context.getApplicationContext();
    }

	/**
	 * Returns the application context.
	 */
	public static Context getContext(){
        if(context == null) throw new RuntimeException("context is null. Did you extend AndroidApp and declared the App in the manifest?");
		return context;
	}


    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    ///////////////////// MANIFEST.XML ///////////////////////////

    /**
     * Returns the current package name
     */
    public static String getPackageName(){
        return Res.getContext().getPackageName();
    }

    /**
     * Returns true if there's a name
     * @return the application name if it doesn't exist returns the package name
     */
    public static String getApplicationName(){
        if(context instanceof AndroidApp)
            return ((AndroidApp) context).getApplicationName();
        return getPackageName();
    }

    /**
     * Returns the schema version
     * The value is read from the manifest.
     * <code>android:versionCode</code>
     */
    public static String getVersionCode(){
    	return getVersionCode(getContext().getPackageName());
    }

    /**
     * Returns the version name.
     * The value is read from the manifest
     * <code>android:versionName</code>
     */
    public static String getVersionName(){
    	return getVersionName(getContext().getPackageName());
    }

    /**
     * Returns version code for a package name
     */
    public static String getVersionCode(String packageName){
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(Res.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES).versionCode);
        }
        catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

    /**
     * Returns version name for a package name
     */
    public static String getVersionName(String packageName){
        StringBuilder builder = new StringBuilder();
        try{
            builder.append(Res.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES).versionName);
        }
        catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

    /**
     * Returns package manager
     */
    public static PackageManager getPackageManager(){
        return getContext().getPackageManager();
    }

    /**
     * Returns package info
     */
    public static PackageInfo getPackageInfo(String packageName, int flags) throws PackageManager.NameNotFoundException {
        return getPackageManager().getPackageInfo(packageName, flags);
    }

    /**
     * Checks to see if this application is debuggable
     */
    @SuppressWarnings("ConstantConditions")
    public static boolean isApplicationDebuggable(){
        return (0 != (getContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }

    /**
     * Returns the application meta value
     */
    @SuppressWarnings("ConstantConditions")
    public static Object getApplicationMetaValue(String packageName, String metaName){
    	try{
            return Res.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA).metaData.get(metaName);
    	}
    	catch (Throwable t){
    		return null;
    	}
    }

    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    ///////////////////// RESOURCES //////////////////////////////

    /**
     * Returns the resources
     */
    public static Resources getResources(){
        return getContext().getResources();
    }

    public static AssetManager getAssets(){
        return getContext().getAssets();
    }

    /**
     * Returns the string w id
     */
    public static String getString(int resourceId){
        return getResources().getString(resourceId);
    }

    /**
     * Returns the string
     */
    public static String getString(int resourceId, Object... params){
        return getResources().getString(resourceId, params);
    }

    /**
     * Returns array of string from resId
     */
    public static String[] getStringArray(int resourceId){
        return getResources().getStringArray(resourceId);
    }

    /**
     * Gets array string from multiple resIds
     */
    public static String[] getArrayString(int... resourceIds){
        String[] strings = new String[resourceIds.length];
        for(int i = 0; i < strings.length; i++){
            strings[i] = getContext().getString(resourceIds[i]);
        }
        return strings;
    }

    /**
     * Gets integer in a resource id
     */
    public static int getInt(int resourceId){
        return getResources().getInteger(resourceId);
    }

    /**
     * Gets array integer in resource
     */
    public static int[] getIntArray(int resourceId){
        return getResources().getIntArray(resourceId);
    }

    /**
     * Returns drawable
     */
    public static Drawable getDrawable(int drawable){
        return getResources().getDrawable(drawable);
    }

    /**
     * Returns color
     */
    public static int getColor(int color){
    	return getResources().getColor(color);
    }

    /**
     * Opens raw resource by its id
     */
    public static InputStream openRawResource(int resourceId) throws IOException{
        return getResources().openRawResource(resourceId);
    }

    /**
     * Read raw
     */
    public static CharSequence readRaw(int resourceId) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(Res.openRawResource(resourceId)));
            String line;
            StringBuilder buffer = new StringBuilder();
            while ((line = in.readLine()) != null) buffer.append(line).append('\n');
            return buffer;
        } catch (IOException e) {
            return "";
        } finally {
            if(in != null)
                try{
                    in.close();
                }
                catch (IOException e){
                    // ignore
                }
        }
    }

    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    ///////////////////// PREFERENCES ////////////////////////////

    /**
     * Returns the shared preferences
     */
    public static SharedPreferences getPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    /**
     * Sets a boolean pref
     */
    public static void setPreference(String key, boolean value){
    	SharedPreferences.Editor editor = getPreferences().edit();
    	editor.putBoolean(key, value);
    	editor.apply();
    }

    /**
     * Sets a string pref
     */
    public static void setPreference(String key, String value){
    	SharedPreferences.Editor editor = getPreferences().edit();
    	editor.putString(key, value);
    	editor.apply();
    }

    /**
     * Sets an int pref
     */
    public static void setPreference(String key, int value){
    	SharedPreferences.Editor editor = getPreferences().edit();
    	editor.putInt(key, value);
    	editor.apply();
    }

    /**
     * Sets an int pref
     */
    public static void setPreference(String key, long value){
    	SharedPreferences.Editor editor = getPreferences().edit();
    	editor.putLong(key, value);
    	editor.apply();
    }

    /**
     * Returns a preference value
     */
    public static int getPreference(String key, int defaultValue){
    	return Res.getPreferences().getInt(key, defaultValue);
    }

    /**
     * Returns a preference value
     */
    public static long getPreference(String key, long defaultValue){
    	return Res.getPreferences().getLong(key, defaultValue);
    }

    /**
     * Returns a preference value
     */
    public static boolean getPreference(String key, boolean defaultValue){
    	return Res.getPreferences().getBoolean(key, defaultValue);
    }

    /**
     * Returns a preference value
     */
    public static String getPreference(String key, String defaultValue){
    	return Res.getPreferences().getString(key, defaultValue);
    }

    /**
     * Returns a preference value
     */
    public static float getPreference(String key, float defaultValue){
    	return Res.getPreferences().getFloat(key, defaultValue);
    }

    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    ///////////////////// DEVICES ////////////////////////////////

    /**
     * Returns screen density
     */
    public static float getScreenDensity(){
    	return getResources().getDisplayMetrics().density;
    }

    /**
     * Returns the current screen width
     * according to the current orientation
     */
	public static int getScreenWidth(){
        return getResources().getDisplayMetrics().widthPixels;
	}

    /**
     * Returns the current screen width
     * according to the current orientation
     */
	public static int getScreenHeight(){
        return getResources().getDisplayMetrics().heightPixels;
	}

    /**
     * Returns the current orientation
     */
    public static int getScreenOrientation(){
        return (Res.getResources().getConfiguration().orientation);
    }
    
}
