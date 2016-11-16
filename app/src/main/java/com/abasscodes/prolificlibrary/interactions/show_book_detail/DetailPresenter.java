package com.abasscodes.prolificlibrary.interactions.show_book_detail;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 11/15/16.
 */

public interface DetailPresenter extends Mvp.Presenter {

    void showBookDetail(Book book);
}
