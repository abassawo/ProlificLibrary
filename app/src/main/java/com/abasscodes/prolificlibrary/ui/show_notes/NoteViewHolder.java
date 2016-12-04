package com.abasscodes.prolificlibrary.ui.show_notes;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.helpers.TextUtilHelper;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.NotesRepository;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.abasscodes.prolificlibrary.model.database.BookContentProvider;
import com.facebook.stetho.inspector.MismatchedResponseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/29/16.
 */
public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = NoteViewHolder.class.getSimpleName();
    @Bind(R.id.note_vh_title)
    TextView titleTV;
    @Bind(R.id.notes_recycler_view)
    RecyclerView notesRecyclerView;
    @Bind(R.id.add_note)
    View addNoteBtn;
    private PageNoteAdapter adapter;
    private Book book;
    private boolean visible = false;

    private BookContentProvider bookProvider;


    public NoteViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        ButterKnife.bind(this, itemView);
        titleTV.setOnClickListener(this);
        addNoteBtn.setOnClickListener(this);
        bookProvider = new BookContentProvider();
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
        adapter = new PageNoteAdapter(pageNotes);
        Context ctx = itemView.getContext();
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
        notesRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_note:
                FragmentManager fm = RegisterActivity.basePresenterActivity.getSupportFragmentManager();
                showAddDialog();
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

    public void showAddDialog() {
        final Context ctx = itemView.getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setTitle("Add a new note for " + book.getTitle())
                .setMessage("it will be added to your notes");
        LinearLayout ll = new LinearLayout(ctx);
        ll.setOrientation(LinearLayout.VERTICAL);
        final EditText pageField = new EditText(ctx);
        final EditText noteField = new EditText(ctx);
        ll.addView(pageField);
        ll.addView(noteField);

        builder.setView(ll);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                if (TextUtilHelper.isValidNote(pageField, noteField)) {
                    int pageNum = Integer.parseInt(pageField.getText().toString());
                    String note = noteField.getText().toString();
                    PageNote pageNote;
                    if (book.hasPageNote(pageNum)) {
                        pageNote = book.getPageNote(pageNum);
                        pageNote.append(note);
                        bookProvider.updatePageNote(pageNote);
                    } else {
                        pageNote = new PageNote(pageNum, note, book.getId());
                        bookProvider.savePageNote(pageNote);
                    }
                    book.pageNoteMap.put(pageNum, pageNote);
                    bindBook(book);
                    showHiddenContent();

                } else {
                    Toast.makeText(ctx, "Please verify all forms", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // No action - Dismiss.
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }


}
