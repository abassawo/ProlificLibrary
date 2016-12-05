package com.abasscodes.prolificlibrary.user_interactions.show_book_detail;

import android.view.View;
import com.abasscodes.prolificlibrary.MvpPresenter;
import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 11/15/16.
 */

public interface Presenter extends MvpPresenter {

    void showBookDetail(View view, Book book);
}
