package com.abasscodes.prolificlibrary.ui.checkout_book;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/16/16.
 */
public class CheckoutDialogFragment extends DialogFragment{

    public static final String TAG = CheckoutDialogFragment.class.getSimpleName();
    private static final String BOOK = "BOOK";
    private static CheckoutDialogFragment instance;
    private boolean returnBook;
    private Book book;

    private String title, message, positiveBtn;
    String name; //fixme

    public static CheckoutDialogFragment newInstance(Book book){
            boolean returnBook = book.isCheckedOut();
            return newInstance(book, returnBook);

    }

    private static CheckoutDialogFragment newInstance(Book book, boolean returnBook){
        if(instance == null){
            instance = new CheckoutDialogFragment();
        }
        Bundle args = new Bundle();
        args.putParcelable(BOOK, book);
        args.putBoolean("RETURN", returnBook);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = PreferenceHelper.getUserName(getActivity());
        book = (Book) getArguments().get(BOOK);
        returnBook = getArguments().getBoolean("RETURN");
        if(returnBook){
            title = "Return " + book.getTitle() + "?";
            message = "The book will no longer be checked out";
            positiveBtn = "Return";
        }else{
            title =  getResources().getString(R.string.checkout_book) + " " + book.getTitle() + "?";
            message = "Give me access";
            positiveBtn = "Check out";
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title)
                .setMessage(message);
        builder.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Store the submitted name and check out book
                if(!returnBook) {
                    checkOut(book, name);
                }else{
                    returnBook(book);
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
        return super.onCreateDialog(savedInstanceState);
    }

    public void checkOut(Book book, String name){
        book.setLastCheckedOut(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        book.setLastCheckedOutBy(name);
        updateBookOnServer(book);
    }

    public void updateBookOnServer(Book book){
        Call<Book> call = APIClient.getInstance().updateBook(book.getId(), book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getActivity(), "error " + t, Toast.LENGTH_LONG).show();
                Log.d(TAG, "failed to post " + t);
            }
        });
    }

    public void returnBook(Book book){
        book.setLastCheckedOut(null);
        book.setLastCheckedOutBy(null);
        updateBookOnServer(book);
    }

}
