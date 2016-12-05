package com.abasscodes.prolificlibrary;

import android.content.Context;
import android.util.Base64;

/**
 * Created by C4Q on 12/5/16.
 */

public class Config {


    public static String getNYTApiKey(Context context){
        return getKey(context, R.string.nyt_api_key);
    }

    public static String getDreamApiKey(Context context){
       return getKey(context, R.string.dream_api_key);
    }

    private static String getKey(Context context, int textRes){
        String enc = context.getResources().getString(textRes);
        return getKey(enc);
    }

    private static String getKey(String encoded){
        return new String(Base64.decode(encoded, Base64.DEFAULT));
    }

}
