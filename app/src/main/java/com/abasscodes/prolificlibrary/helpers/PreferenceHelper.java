package com.abasscodes.prolificlibrary.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by C4Q on 11/21/16.
 */

public class PreferenceHelper {

    private static String onboard_key = "onboard?";
    private static String username_key = "username";
    private static String email_key = "email";
    private static Map<String, Boolean> potentialGenres = new HashMap<>();
    private static String nyt_feed = "nyt_feed";


    public static boolean isFirstRun(Context context) {
        boolean firstRun = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(onboard_key, true);
        return firstRun;
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
        prefs.edit().putString(username_key, userName).commit();
    }

    public static void setEmail(Context context, String email) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(email_key, email).commit();
    }


    public static void saveNYTFeed(Context context, Set<String> nytFeed) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putStringSet(nyt_feed, nytFeed).commit();
    }

    public static Set<String> getNYTFeeds(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(nyt_feed, null);
    }

}