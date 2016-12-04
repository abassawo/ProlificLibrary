package com.abasscodes.prolificlibrary.ui.show_all_books;

import android.app.Activity;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/15/16.
 */

public interface Presenter extends Mvp.Presenter {

    void showNetworkSettings();
    void fillOutNewBookForm();

}
