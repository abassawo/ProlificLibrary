package com.abasscodes.prolificlibrary.ui.show_notes;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.NotesRepository;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.abasscodes.prolificlibrary.model.database.BookContentProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/29/16.
 */
public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = NoteViewHolder.class.getSimpleName();
    @Bind(R.id.note_vh_title)TextView titleTV;
    boolean notesVisible = false;
    @Bind(R.id.notes_recycler_view) RecyclerView notesRecyclerView;
    @Bind(R.id.add_note)
    View addNoteBtn;
    private PageNoteAdapter adapter;
    private Book book;
    private int indexInColl;



    public NoteViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        ButterKnife.bind(this, itemView);
        titleTV.setOnClickListener(this);
        addNoteBtn.setOnClickListener(this);
    }

    public static View inflateView(ViewGroup parent){
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.book_note_row, parent, false);
    }

    public void bindBook(Book book) {
        this.book = book;
        titleTV.setText(book.getTitle());
        setupAdapter(book);
        updateUI();
    }

    public void updateUI(){
        boolean visible = book.notesVisible;
        if(!NotesAdapter.selectedBooks.isEmpty()) {
            visible = book == NotesAdapter.selectedBooks.peek();
        }
        if(visible){
            notesRecyclerView.setVisibility(View.VISIBLE);
            addNoteBtn.setVisibility(View.VISIBLE);
        }else{
            notesRecyclerView.setVisibility(View.INVISIBLE);
            addNoteBtn.setVisibility(View.INVISIBLE);
        }
    }

    public void setupAdapter(Book book){
        this.book = book;
        List<PageNote> pageNotes = new ArrayList<>();
        for (PageNote pageNote : BookContentProvider.getInstance().getAllNotes()) {
           if(book.getId() == pageNote.getBookId()){
               pageNotes.add(pageNote);
               Log.d(TAG, "page notes size was " + pageNotes.size());
           }
        }
        Collections.sort(pageNotes);
        adapter = new PageNoteAdapter(pageNotes);
        Context ctx = itemView.getContext();
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
        notesRecyclerView.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_note:
                FragmentManager fm = RegisterActivity.basePresenterActivity.getSupportFragmentManager();
                AddNotesDialog.newInstance(book).show(fm, null);
                break;
        }
        book.notesVisible = !book.notesVisible;
        updateUI();
    }


}
