package com.abasscodes.prolificlibrary.ui.show_notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by C4Q on 11/29/16.
 */
public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder>{
    private List<Book> books = new ArrayList<>();
    public static Stack<Book> selectedBooks = new Stack<>();

    public NotesAdapter(List<Book> books) {
       this.books = books;
    }
    private static NoteViewHolder holder;

    public void setSelectedBook(Book book) {
        while(!selectedBooks.isEmpty()){
            Book b = selectedBooks.pop();
            if(holder != null) holder.bindBook(b);
        }
        selectedBooks.push(book);
    }

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
}
