package com.abasscodes.prolificlibrary.model;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/13/16.
 */

public class BookFilterer {

    private FiltersReadyListener listener;

    public BookFilterer(FiltersReadyListener listener){
        this.listener = listener;
    }
    public void filter(List<Book> books){
        ArrayList<Book> completedBooks = new ArrayList<>();
        ArrayList<Book> checkedOutBooks = new ArrayList<>();
        ArrayList<Book> archivedBooks = new ArrayList<>();

        for(Book book : books){
            if(book.isComplete()) completedBooks.add(book);
            if(book.isCheckedOut()) checkedOutBooks.add(book);
            if(book.isArchived()) archivedBooks.add(book);
        }
        listener.setCompletedBooks(completedBooks);
        listener.setCheckedOutBooks(completedBooks);
        listener.setArchivedBooks(completedBooks);
    }

    public interface FiltersReadyListener{
        void setCompletedBooks(ArrayList<Book> completedBooks);
        void setCheckedOutBooks(ArrayList<Book> checkedOutBooks);
        void setArchivedBooks(ArrayList<Book> archivedBooks);
    }
}
