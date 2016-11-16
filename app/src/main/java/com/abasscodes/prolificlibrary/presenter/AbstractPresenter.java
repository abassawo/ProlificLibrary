package com.abasscodes.prolificlibrary.presenter;

import android.content.Intent;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.interactions.edit_book.EditActivity;

/**
 * Created by C4Q on 11/15/16.
 */

public abstract class AbstractPresenter implements Mvp.Presenter{
    protected static BasePresenterActivity<AbstractPresenter> activity;


    public AbstractPresenter(BasePresenterActivity<AbstractPresenter> activity){
        this.activity = activity;
    }

    public void fillOutNewBookForm(){
        Intent intent = EditActivity.fillOutNewBook(activity);
        activity.startActivity(intent);
    }

    @Override
    public void showNetworkSettings() {
        activity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    }
}
