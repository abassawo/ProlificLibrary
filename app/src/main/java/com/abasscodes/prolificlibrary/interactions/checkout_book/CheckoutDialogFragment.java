package com.abasscodes.prolificlibrary.interactions.checkout_book;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.api.APIClient;

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
    private Book book;

    public static CheckoutDialogFragment newInstance(Book book){
        if(instance == null){
            instance = new CheckoutDialogFragment();
        }
        Bundle args = new Bundle();
        args.putParcelable(BOOK, book);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (Book) getArguments().get(BOOK);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getResources().getString(R.string.checkout_book) + " " + book.getTitle() + "?";
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText edittext = new EditText(getActivity());

        builder.setTitle(title)
                .setMessage(getResources().getString(R.string.dialog_msg));
        builder.setView(edittext);

        builder.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Store the submitted name and check out book
                String name = edittext.getText().toString();
                checkOut(book, name);
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
        Call<Book> call = APIClient.getInstance().updateBook(book.getId(), book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
//                bindBook(response.body());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getActivity(), "error " + t, Toast.LENGTH_LONG).show();
                Log.d(TAG, "failed to post " + t);
            }
        });
    }

}
