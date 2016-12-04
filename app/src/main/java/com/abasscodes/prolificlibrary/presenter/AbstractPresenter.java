package com.abasscodes.prolificlibrary.presenter;

import android.content.Intent;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.ui.edit_book.EditActivity;
import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 11/15/16.
 */

public abstract class AbstractPresenter implements Mvp.Presenter{
    protected static BasePresenterActivity<AbstractPresenter> activity;


    public AbstractPresenter(BasePresenterActivity<AbstractPresenter> activity){
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


//    @Override
//    public void showCheckOutDialog(Book book) {
//        CheckoutDialogFragment fragment = CheckoutDialogFragment.newInstance(book);
//        fragment.show(activity.getSupportFragmentManager(), null);
//    }

}
