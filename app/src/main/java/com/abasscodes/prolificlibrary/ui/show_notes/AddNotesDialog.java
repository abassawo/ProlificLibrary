package com.abasscodes.prolificlibrary.ui.show_notes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.abasscodes.prolificlibrary.model.database.BookContentProvider;
import com.abasscodes.prolificlibrary.model.database.DatabaseHelper;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.ui.checkout_book.CheckoutDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static okhttp3.internal.Internal.instance;

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
        pageField =new EditText(getActivity());
        noteField =new EditText(getActivity());

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
                //Store the submitted name and check out book
                int pageNum = Integer.parseInt(pageField.getText().toString());
                String note = noteField.getText().toString();
                PageNote pageNote = new PageNote(pageNum, note);
                book.getPageNotes().add(pageNote);
                DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.basePresenterActivity);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                BookContentProvider.getInstance().saveBookContent(book);
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
