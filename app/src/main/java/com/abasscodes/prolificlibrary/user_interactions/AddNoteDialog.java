package com.abasscodes.prolificlibrary.user_interactions;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.helpers.TextUtilHelper;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.abasscodes.prolificlibrary.model.database.BookContentProvider;

import java.util.List;

/**
 * Created by C4Q on 12/4/16.
 */
public class AddNoteDialog extends DialogFragment {

    private static final String BOOK_KEY = "book_key";
    private Book book;
    private BookContentProvider bookProvider;
    private NoteAddedListener listener;

    public static AddNoteDialog newInstance(Book book) {
        Bundle args = new Bundle();
        args.putParcelable(BOOK_KEY, book);
        AddNoteDialog fragment = new AddNoteDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = getArguments().getParcelable(BOOK_KEY);
        bookProvider = BookContentProvider.getInstance();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context ctx = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setTitle("Add a new note for " + book.getTitle())
                .setMessage("it will be added to your notes");
        LinearLayout ll = new LinearLayout(ctx);
        ll.setOrientation(LinearLayout.VERTICAL);
        final EditText pageField = new EditText(ctx);
        final EditText noteField = new EditText(ctx);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(30, 16, 30, 16);
        pageField.setHint("Page Number");
        pageField.setInputType(InputType.TYPE_CLASS_NUMBER);
        noteField.setHint("Note");
        ll.addView(pageField, layoutParams);
        ll.addView(noteField, layoutParams);

        builder.setView(ll);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (TextUtilHelper.isValidNote(pageField, noteField)) {
                            int pageNum = Integer.parseInt(pageField.getText().toString());
                            String note = noteField.getText().toString();
                            PageNote pageNote = new PageNote(pageNum, note, book.getId());
                            bookProvider.savePageNote(pageNote);
                            pageNote.append(note);
                            listener.onNoteAdded();
                        } else {
                            Toast.makeText(ctx, "Please verify all forms", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

        );

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // No action - Dismiss.
                    }
                }

        );

        Dialog dialog = builder.create();
        return dialog;
    }

    public void setListener(NoteAddedListener listener) {
        this.listener = listener;
    }

    public interface NoteAddedListener {
        void onNoteAdded();
    }
}