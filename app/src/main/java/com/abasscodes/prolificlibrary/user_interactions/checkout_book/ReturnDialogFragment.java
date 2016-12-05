package com.abasscodes.prolificlibrary.user_interactions.checkout_book;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 12/4/16.
 */

public class ReturnDialogFragment extends CheckoutDialogFragment {

    public static final String TAG = CheckoutDialogFragment.class.getSimpleName();



    public static ReturnDialogFragment newInstance(Book book) {
        ReturnDialogFragment fragment = new ReturnDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(BOOK_KEY, book);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (Book) getArguments().get(BOOK_KEY);
        title = getResources().getString(R.string.checkout_book) + " " + book.getTitle() + "?";
        message = "\"The book will no longer be checked out\"";
        positiveBtn = "Return";
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle( "Return " + book.getTitle())
                .setMessage(message);
        builder.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Store the submitted name and check out book
                returnBook(book);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        Dialog dialog = builder.create();
        return dialog;
    }


    public void returnBook(Book book) {
        book.returnCheckOut();
        updateBookOnServer(book);
    }
}
