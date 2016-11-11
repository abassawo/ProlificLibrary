package com.abasscodes.prolificlibrary.ui.tabbed_ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.ui.BookViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<Book> mBooks;
    private Context mContext;

    public BookAdapter(Context context, List<Book> books){
        mBooks = books;
        mContext = context;
    }

    public BookAdapter(Context context){
        mContext = context;

    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = mBooks.get(position);
        holder.bindBook(book);
    }

    @Override
    public int getItemCount() {
        if(mBooks == null) return 0;
        return mBooks.size();
    }

    public void setBooks(List<Book> books){
        mBooks = books;
    }



}