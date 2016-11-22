package com.abasscodes.prolificlibrary.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<Book> books;
    private Context context;

    public BookAdapter(Context context, List<Book> books){
        this.books = books;
        this.context = context;
    }

    public BookAdapter(Context context){
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(parent);
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