package com.abasscodes.prolificlibrary.presenter;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 */
public interface Presenter{

    void showBookDetail(Book book);
    void editBook(int id, Book book);
    void fillOutNewBookForm();
    void onAllBooksLoaded(ArrayList<Book> books);
    void updateUI();
    void onConnectionFailure();
    void showNetworkSettings();
}