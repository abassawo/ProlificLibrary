package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.model.BookNote;

import java.util.List;

/**
 * Created by C4Q on 11/30/16.
 */
public class BookNoteAdapter extends RecyclerView.Adapter<BookNoteViewHolder> {
    private List<BookNote> bookNotes;

    public BookNoteAdapter(List<BookNote> bookNotes) {
        this.bookNotes = bookNotes;
    }

    @Override
    public BookNoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookNoteViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BookNoteViewHolder holder, int position) {
        BookNote note = bookNotes.get(position);
        holder.bindBookNote(note);
    }

    @Override
    public int getItemCount() {
        return bookNotes.size();
    }
}
