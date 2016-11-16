package com.abasscodes.prolificlibrary.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by C4Q on 11/12/16.
 */

public class ConnectionUtil {

    private static final String TAG = ConnectionUtil.class.getSimpleName();

    public static boolean isConnected(){
        Context context = RegisterActivity.basePresenterActivity;
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            boolean connected = networkInfo == null ? false : networkInfo.isConnected();
            Log.d(TAG, "Connected " + connected);
            return connected;
    }
}
