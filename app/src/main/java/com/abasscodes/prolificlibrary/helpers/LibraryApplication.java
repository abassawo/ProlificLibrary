package com.abasscodes.prolificlibrary.helpers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.abasscodes.prolificlibrary.presenter.AbstractPresenterActivity;

/**
 * Created by C4Q on 11/11/16.
 */

public class LibraryApplication  extends Application implements Application.ActivityLifecycleCallbacks{

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if(activity instanceof AbstractPresenterActivity)
            RegisterActivity.presenterActivity = (AbstractPresenterActivity) activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if(activity instanceof AbstractPresenterActivity)
            RegisterActivity.presenterActivity = (AbstractPresenterActivity) activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if(activity instanceof AbstractPresenterActivity)
            RegisterActivity.presenterActivity = null;
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if(activity instanceof AbstractPresenterActivity)
            RegisterActivity.presenterActivity = null;
    }
}
