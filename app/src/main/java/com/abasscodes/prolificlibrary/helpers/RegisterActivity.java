package com.abasscodes.prolificlibrary.helpers;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.presenter.AbstractPresenter;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

/**
 * Created by C4Q on 11/11/16.
 */

public class RegisterActivity {

    public static BasePresenterActivity basePresenterActivity = null;
    public static Mvp.Presenter presenter = basePresenterActivity == null ? null : basePresenterActivity.getPresenter();


}

