package com.abasscodes.prolificlibrary.ui.show_notes;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;

import java.util.ArrayList;
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

    private BookNoteAdapter adapter;
    private Book book;


    public NoteViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        ButterKnife.bind(this, itemView);
        titleTV.setOnClickListener(this);
    }

    public static View inflateView(ViewGroup parent){
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.book_note_row, parent, false);
    }

    public void bindBook(Book book) {
        titleTV.setText(book.getTitle());
        setupAdapter(book);
        updateUI();
    }

    public void setupAdapter(Book book){
        this.book = book;
        List<PageNote> pageNotes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pageNotes.add(PageNote.testBookNote());
        }
        adapter = new BookNoteAdapter(pageNotes);
        Context ctx = itemView.getContext();
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
        notesRecyclerView.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.note_vh_title:
                NotesAdapter.selectedBook = book;
                updateUI();
        //}
    }

    private void updateUI() {
        if(NotesAdapter.selectedBook == book){
            notesRecyclerView.setVisibility(View.VISIBLE);
        }else{
            notesRecyclerView.setVisibility(View.INVISIBLE);
        }
    }
}
