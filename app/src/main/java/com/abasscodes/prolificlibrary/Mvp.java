package com.abasscodes.prolificlibrary;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/14/16.
 */

public interface Mvp {

    public interface Presenter{

        void showBookDetail(Book book);
        void editBook(int id, Book book);
        void fillOutNewBookForm();
        void onAllBooksLoaded(ArrayList<Book> books);
        void onConnectionFailure();
        void showNetworkSettings();
    }
}
