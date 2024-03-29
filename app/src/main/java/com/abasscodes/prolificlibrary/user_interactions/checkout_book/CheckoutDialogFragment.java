package com.abasscodes.prolificlibrary.user_interactions.checkout_book;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.user_interactions.show_book_detail.DetailActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/16/16.
 */
public class CheckoutDialogFragment extends DialogFragment {

    public static final String TAG = CheckoutDialogFragment.class.getSimpleName();
    protected static final String BOOK_KEY = "BOOK_KEY";
    protected Book book;
    protected CheckoutStateListener listener;

    protected String title, message, positiveBtn;


    public static CheckoutDialogFragment newInstance(Book book) {
        CheckoutDialogFragment fragment = new CheckoutDialogFragment();
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
        message = "Give me access";
        positiveBtn = "Check out";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.checkout_dialog_fragment, container, false);
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
                Context ctx = getContext();
                checkOut(book, PreferenceHelper.getUserName(ctx));
                updateBookOnServer(book);

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // No action - Dismiss.
            }
        });

        Dialog dialog = builder.create();
        return dialog;
    }

    public void checkOut(Book book, String name) {
        book.setLastCheckedOut(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        book.setLastCheckedOutBy(name);
        updateBookOnServer(book);
    }

    public void updateBookOnServer(final Book book) {
        Call<Book> call = APIClient.getInstance().updateBook(book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(listener != null) {
                    listener.onCheckoutChange(book);
                }
                dismiss();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getActivity(), "error " + t, Toast.LENGTH_LONG).show();
                Log.d(TAG, "failed to post " + t);
            }
        });
    }

    public void setListener(CheckoutStateListener listener) {
        this.listener = listener;
    }

    public interface CheckoutStateListener{
        void onCheckoutChange(Book book);
    }


}
