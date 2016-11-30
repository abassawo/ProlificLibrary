package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookNote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by C4Q on 11/29/16.
 */
public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder>{
    private List<Book> books = new ArrayList<>();

    public NotesAdapter(List<Book> books) {
       this.books = books;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(parent);
    }

    public static View inflateView(ViewGroup parent){
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.book_note_row, parent, false);
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
