package com.abasscodes.prolificlibrary.presenter;

import android.content.Intent;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.user_interactions.edit_book.EditActivity;

/**
 * Created by C4Q on 11/15/16.
 */

public abstract class AbstractPresenter implements Mvp.Presenter{
    protected static BasePresenterActivity activity;


    public AbstractPresenter(BasePresenterActivity activity){
        this.activity = activity;
    }

    public void fillOutNewBookForm(){
        Intent intent = EditActivity.fillOutNewBook(activity, null);
        activity.startActivity(intent);
    }

    @Override
    public void showNetworkSettings() {
        activity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    }


}
