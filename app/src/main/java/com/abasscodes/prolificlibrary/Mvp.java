package com.abasscodes.prolificlibrary;

import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;

/**
 * Created by C4Q on 11/15/16.
 */

public interface Mvp {

    public interface Model{

    }

    public interface View{

    }

    public interface Presenter{
        void onConnectionFailure();
        void showNetworkSettings();
        void showCheckOutDialog(Book book);
    }
}
