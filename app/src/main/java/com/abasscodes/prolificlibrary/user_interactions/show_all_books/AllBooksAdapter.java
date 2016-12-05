package com.abasscodes.prolificlibrary.user_interactions.show_all_books;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 */

public class AllBooksAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<Book> books;
    private Context context;

    public AllBooksAdapter(Context context, List<Book> books){
        this.books = books;
        this.context = context;
    }

    public AllBooksAdapter(Context context){
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
        notifyDataSetChanged();
    }


    public void clear() {
        books.clear();
        notifyDataSetChanged();
    }

    public void addBook(Book book) {
        if(books != null){
            books.add(book);
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}