package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookNote;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/29/16.
 */
public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.note_vh_title)TextView titleTV;
    boolean notesVisible = false;
    @Bind(R.id.notes_recycler_view) RecyclerView notesRecyclerView;


    public NoteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static View inflateView(ViewGroup parent){
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.book_note_row, parent, false);
    }

    public void bindBook(Book book) {
        titleTV.setText(book.getTitle());
        List<BookNote> bookNotes = NotesRepository.getnotes(book);
        notesRecyclerView.setAdapter(new BookNoteAdapter(bookNotes));
        if(notesVisible){
            notesRecyclerView.setVisibility(View.VISIBLE);
        }else{
            notesRecyclerView.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.note_vh_title:
                notesVisible = !notesVisible;
        }
    }
}
