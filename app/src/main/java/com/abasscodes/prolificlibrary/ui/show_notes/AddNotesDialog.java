package com.abasscodes.prolificlibrary.ui.show_notes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.abasscodes.prolificlibrary.model.database.BookContentProvider;
import com.abasscodes.prolificlibrary.MainTabsActivity;

/**
 * Created by C4Q on 12/1/16.
 */

public class AddNotesDialog extends DialogFragment {


    private Book book;
    private static AddNotesDialog instance;
    private String title, message, positiveBtn;
    EditText pageField, noteField;



    public static AddNotesDialog newInstance(Book book){
        if(instance == null){
            instance = new AddNotesDialog();
        }
        Bundle args = new Bundle();
        args.putParcelable("BOOK", book);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (Book) getArguments().get("BOOK");
        title = "Add a new note for " + book.getTitle() + "";
        message = "It will be added to your notes";
        positiveBtn = "Yes";
        pageField = new EditText(getActivity());
        noteField = new EditText(getActivity());

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title)
                .setMessage(message);
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(pageField);
        ll.addView(noteField);
        builder.setView(ll);
        builder.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                int pageNum = Integer.parseInt(pageField.getText().toString());
                String note = noteField.getText().toString();
                PageNote pageNote = book.getPageNote(pageNum);
                if(pageNote == null){
                    //Store a new note
                    pageNote= new PageNote(pageNum, note, book.getId());
                }else{
                    pageNote.append(note);
                }
                book.pageNoteMap.put(pageNum, pageNote);
                BookContentProvider.getInstance().saveBookContent(book);
//                MainTabsActivity activity = (MainTabsActivity) RegisterActivity.basePresenterActivity;
//                activity.reloadNotes();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // No action - Dismiss.
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
        return super.onCreateDialog(savedInstanceState);
    }



}
