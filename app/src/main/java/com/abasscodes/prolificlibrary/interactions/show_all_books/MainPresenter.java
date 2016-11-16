package com.abasscodes.prolificlibrary.interactions.show_all_books;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/15/16.
 */

public interface MainPresenter extends Mvp.Presenter {

    void onAllBooksLoaded(ArrayList<Book> books);
    void showNetworkSettings();
    void fillOutNewBookForm();
}
