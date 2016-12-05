package com.abasscodes.prolificlibrary.user_interactions.show_notes;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.abasscodes.prolificlibrary.model.database.BookContentProvider;
import com.abasscodes.prolificlibrary.user_interactions.AddNoteDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/29/16.
 */
public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, AddNoteDialog.NoteAddedListener {
    private static final String TAG = NoteViewHolder.class.getSimpleName();
    @Bind(R.id.note_vh_title)
    TextView titleTV;
    @Bind(R.id.notes_recycler_view)
    RecyclerView notesRecyclerView;
    @Bind(R.id.add_note)
    View addNoteBtn;
    private InnerNoteAdapter adapter;
    private Book book;
    private boolean visible = false;


    public NoteViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        ButterKnife.bind(this, itemView);
        titleTV.setOnClickListener(this);
        addNoteBtn.setOnClickListener(this);
    }

    public static View inflateView(ViewGroup parent) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.book_note_row, parent, false);
    }

    public void bindBook(Book book) {
        this.book = book;
        titleTV.setText(book.getTitle());
        setupAdapter(book);
        updateUI();
    }

    public void updateUI() {
        notesRecyclerView.setVisibility(View.INVISIBLE);
        addNoteBtn.setVisibility(View.INVISIBLE);

    }

    public void setupAdapter(Book book) {
        this.book = book;
        List<PageNote> pageNotes = new BookContentProvider().getNotes(book.getId());
        adapter = new InnerNoteAdapter(pageNotes);
        Context ctx = itemView.getContext();
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
        notesRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_note:
                FragmentManager fm = RegisterActivity.basePresenterActivity.getSupportFragmentManager();
                  AddNoteDialog dialog =  AddNoteDialog.newInstance(book);
                  dialog.setListener(this);
                  dialog.show(fm, null);
                break;
        }
        if(!visible) {
            showHiddenContent();
        }else{
            updateUI();
        }
    }

    public void showHiddenContent() {
        notesRecyclerView.setVisibility(View.VISIBLE);
        addNoteBtn.setVisibility(View.VISIBLE);
        visible = true;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                notesRecyclerView.setVisibility(View.INVISIBLE);
                addNoteBtn.setVisibility(View.INVISIBLE);
                visible = false;
            }
        };
        handler.postDelayed(r, 2000);
    }


    @Override
    public void onNoteAdded() {
        bindBook(book);
        showHiddenContent();
    }
}
