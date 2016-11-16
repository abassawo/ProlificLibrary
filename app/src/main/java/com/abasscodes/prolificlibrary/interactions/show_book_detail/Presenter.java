package com.abasscodes.prolificlibrary.interactions.show_book_detail;

import android.content.Context;
import android.view.View;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 11/15/16.
 */

public interface Presenter extends Mvp.Presenter {

    void showBookDetail(View view, Book book);
    void showCheckOutDialog(Book book);
}
