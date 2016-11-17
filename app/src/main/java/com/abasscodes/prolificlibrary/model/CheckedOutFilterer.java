package com.abasscodes.prolificlibrary.model;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/13/16.
 */

public class CheckedOutFilterer {


    private FiltersReadyListener listener;



    public CheckedOutFilterer(FiltersReadyListener callback){
        this.listener = callback;
    }

    public void filter(List<Book> books){
        ArrayList<Book> checkedOutBooks = new ArrayList<>();
        for(Book book : books){
            if(book.isCheckedOut()) checkedOutBooks.add(book);
        }
        listener.setCheckedOutBooks(checkedOutBooks);

    }

    public interface FiltersReadyListener {
        void setCheckedOutBooks(ArrayList<Book> books);
    }

}
