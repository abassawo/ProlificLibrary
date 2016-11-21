package com.abasscodes.prolificlibrary.interactions.onboard_welcome;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by C4Q on 11/21/16.
 */

public class PreferenceWrapper {

    private static String onboard_key = "onboard?";
    private static String username_key = "username";
    private static String email_key = "email";


    public static boolean isFirstRun(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(onboard_key, true);
    }

    public static void disableWelcome(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(onboard_key, false).commit();
    }


    public static String getUserName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(username_key, null);
    }

    public static void setUserName(Context context, String userName) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(username_key, userName);
    }

    public static void setEmail(Context context, String email) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(email_key, email);
    }
}
