package com.abasscodes.prolificlibrary.presenter;

import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 11/11/16.
 */
public interface Presenter{
    void showAllBooks();
    void showBookDetail(Book book);
    void editBook(int id, String title, String author, String pub, String tags);
    void fillOutNewBookForm();
}