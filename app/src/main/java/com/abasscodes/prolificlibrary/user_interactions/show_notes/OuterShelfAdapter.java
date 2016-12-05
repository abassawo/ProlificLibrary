package com.abasscodes.prolificlibrary.user_interactions.show_notes;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/29/16.
 */
public class OuterShelfAdapter extends RecyclerView.Adapter<NoteViewHolder>{
    private List<Book> books = new ArrayList<>();

    public OuterShelfAdapter(List<Book> books) {
       this.books = books;
    }

    public OuterShelfAdapter() {
        this.books = new ArrayList<>();
    }

    public void setBooks(){

        this.books = books;
    }

    private static NoteViewHolder holder;



    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        holder = new NoteViewHolder(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bindBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }
}
