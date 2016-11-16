package com.abasscodes.prolificlibrary.interactions.show_all_books;

import android.app.Activity;
import android.view.View;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/15/16.
 */

public interface Presenter extends Mvp.Presenter {

    void onAllBooksLoaded(Activity activity, ArrayList<Book> books);
    void onAllBooksLoaded(View view, ArrayList<Book> books);
    void showNetworkSettings();
    void fillOutNewBookForm();

}
