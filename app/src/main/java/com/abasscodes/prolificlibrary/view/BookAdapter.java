package com.abasscodes.prolificlibrary.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<Book> books;
    private int resLayout;

    public BookAdapter(List<Book> books){
        this.resLayout = R.layout.book_item;
        this.books = books;
    }


    private BookAdapter(int resLayout, List<Book> books){
        this.resLayout = resLayout;
        this.books = books;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(resLayout, parent);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bindBook(book);
    }

    @Override
    public int getItemCount() {
        if(books == null) return 0;
        else return books.size();
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }



}