package com.abasscodes.prolificlibrary.helpers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;
import com.facebook.stetho.Stetho;

/**
 * Created by C4Q on 11/11/16.
 */

public class LibraryApplication extends Application implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        Stetho.Initializer initializer = Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build();
        Stetho.initialize(initializer);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity instanceof BasePresenterActivity) {
            RegisterActivity.basePresenterActivity = (BasePresenterActivity) activity;
            RegisterActivity.presenter = RegisterActivity.basePresenterActivity.getPresenter();
        }

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity instanceof BasePresenterActivity) {
            RegisterActivity.basePresenterActivity = (BasePresenterActivity) activity;
            RegisterActivity.presenter = RegisterActivity.basePresenterActivity.getPresenter();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity instanceof BasePresenterActivity)
            RegisterActivity.basePresenterActivity = (BasePresenterActivity) activity;
        if (RegisterActivity.basePresenterActivity != null)
            RegisterActivity.presenter = RegisterActivity.basePresenterActivity.getPresenter();
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
