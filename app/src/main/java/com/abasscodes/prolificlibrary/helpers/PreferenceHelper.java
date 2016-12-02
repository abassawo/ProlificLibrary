package com.abasscodes.prolificlibrary.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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


    public static boolean isFirstRun(Context context){
        boolean firstRun = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(onboard_key, true);
        if(firstRun) {
            initializePotentialGenres(context);
        }
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
        prefs.edit().putString(username_key, userName);
    }

    public static void setEmail(Context context, String email) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(email_key, email);
    }

    private static void initializePotentialGenres(Context context ) {
        for(String genre : context.getResources().getStringArray(R.array.genres)){
            potentialGenres.put(genre, false);
        }
    }

    public static void setSelectGenre(String genre, boolean select){
            potentialGenres.put(genre, select);
    }

    public static Set<String> recordGenrePrefsSubset(){
        for(String key : potentialGenres.keySet()){
            if(potentialGenres.get(key) == false)
                potentialGenres.remove(key);
        }
        return potentialGenres.keySet();
    }



}
