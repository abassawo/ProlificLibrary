package com.abasscodes.prolificlibrary.model;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/13/16.
 */

public class BookFilterer {


    private FiltersReadyListener listener;



    public BookFilterer(FiltersReadyListener callback){
        this.listener = callback;
    }

    public void filter(List<Book> books){
        ArrayList<Book> checkedOutBooks = new ArrayList<>();
        for(Book book : books){
            if(book.isCheckedOut()) checkedOutBooks.add(book);
        }
    }

    public interface FiltersReadyListener{
        void setCheckedOutBooks(ArrayList<Book> checkedOutBooks);
    }
}