package com.abasscodes.prolificlibrary.ui.detail;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.MainActivity;
import com.abasscodes.prolificlibrary.ui.editor.EditActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */
public class DetailFragment extends Fragment {

    private APIClient client;
    private String TAG = "DetailFragment";

    //Extras to be passed onto Edit Activity
    public static final String BOOK_ARG_ID = "book_id";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_TAGS = "tags";
    public static final String BOOK_PUBS = "pubs";
    private int bookId;
    private Book book;

    @Bind(R.id.detail_textview)
    TextView detailsTV;
    @Bind(R.id.book_title)
    TextView titleTV;
    @Bind(R.id.book_author)
    TextView authorTV;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    public static DetailFragment newInstance(Integer id) {
        Bundle args = new Bundle();
        args.putInt(BOOK_ARG_ID, id);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        bookId = getArguments().getInt(BOOK_ARG_ID);
        initRetrofit(bookId);
//        checkoutBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showCheckoutDialog(book);
//            }
//        });
        return view;
    }


    public void initRetrofit(int bookId) {
        APIClient.getInstance().getBook(bookId);
//        service.getBook(bookId, new Callback<Book>() {
//            @Override
//            public void success(Book book, Response response) {
//                bindBook(book);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.d(TAG, "Error retrofitting " + error.getMessage() + error.getUrl());
//            }
//        });
    }

    public void bindBook(Book book) {
        this.book = book;
        titleTV.setText(book.getTitle());
        authorTV.setText(book.getAuthor());
        if(book.getPublisher() != null)
        detailsTV.setText(book.getPublisher());


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showCheckoutDialog(final Book book) {
        String title = getResources().getString(R.string.checkout_book) + " " + book.getTitle() + "?";
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText edittext = new EditText(getActivity());


        builder.setTitle(title)
                .setMessage(getResources().getString(R.string.dialog_msg));
        builder.setView(edittext);


        builder.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                String name = edittext.getText().toString();
                checkOut(book, name);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        Dialog dialog = builder.create();
        dialog.show();

    }

    public void checkOut(final Book book, String name) {
        Date date = new Date();
        String dateFormatStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        book.setLastCheckedOut(dateFormatStr);
        book.setLastCheckedOutBy(name);
        Call<Book> call = client.updateBook(book.getId(), book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(final Call<Book> call, Response<Book> response) {
                Toast.makeText(getActivity(), book.getTitle() + " checked out by " + book.getLastCheckedOutBy(), Toast.LENGTH_SHORT).show();
                bindBook(book);
            }


            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getActivity(), "error " + t, Toast.LENGTH_LONG).show();
                Log.d(TAG, "failed to post " + t);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent homeIntent = new Intent(getActivity(), MainActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        switch (item.getItemId()) {
            case R.id.delete_book:
                deleteBook(bookId);
                startActivity(homeIntent);
                break;
            case android.R.id.home:
                startActivity(homeIntent);
                break;
//
            case R.id.edit_this_book:
                goToEditBookForm(book);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void goToEditBookForm(Book book) {
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra(DetailFragment.BOOK_ARG_ID, bookId);   //Send original id to the hosting activity.
        intent.putExtra(DetailFragment.BOOK_AUTHOR, book.getAuthor());   //Send original id to the hosting activity.
        intent.putExtra(DetailFragment.BOOK_TITLE, book.getTitle());   //Send original id to the hosting activity.
        intent.putExtra(DetailFragment.BOOK_TAGS, book.getCategories());   //Send original id to the hosting activity.
        intent.putExtra(DetailFragment.BOOK_PUBS, book.getPublisher());   //Send original id to the hosting activity.
        startActivity(intent);
    }


    public void deleteBook(int id) {
        final String title = book.getTitle();
        Call<Book> call = client.deleteBook(id);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Toast.makeText(getActivity(), "1 item deleted : " + title, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d(TAG, "failure " + t);
            }
        });
    }
}
