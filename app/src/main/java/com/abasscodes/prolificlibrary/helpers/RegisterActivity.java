package com.abasscodes.prolificlibrary.helpers;

import android.support.v4.app.FragmentManager;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

/**
 * Created by C4Q on 11/11/16.
 */

public class RegisterActivity {

    public static BasePresenterActivity basePresenterActivity = null;
    public static FragmentManager fm = basePresenterActivity == null ? null : basePresenterActivity.getSupportFragmentManager();


}

