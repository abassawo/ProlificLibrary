package com.abasscodes.prolificlibrary.ui.show_book_detail;

import android.annotation.TargetApi;
import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.ui.edit_book.EditActivity;
import com.abasscodes.prolificlibrary.ui.show_all_books.MainTabsActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;

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
public class DetailFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.book_title)
    TextView titleTV;
    @Bind(R.id.book_author)
    TextView authorTV;
    @Bind(R.id.checkout_book)
    ImageView checkoutBook;
    private Book book;
    private APIClient client;
    private String TAG = "DetailFragment";

    //Extra for Detail Fragment Book AND for passing book onto Edit Activity
    public static final String BOOK_KEY = "book_detail";
    private DetailPresenter presenter;
    private DetailInteractionListener callback;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = APIClient.getInstance();
        presenter = DetailPresenter.getInstance((DetailActivity) getActivity());
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (DetailInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DetailInteractionListener");
        }
    }

    public static DetailFragment newInstance(Book book) {
        Bundle args = new Bundle();
        args.putParcelable(BOOK_KEY, book);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        book = getArguments().getParcelable(BOOK_KEY);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkoutBook.setOnClickListener(this);
        if (book != null && presenter != null) {
            presenter.showBookDetail(getView(), book);
        }

    }

    public void bindBook(Book book) {
        this.book = book;
        if (book == null) return;
        ;
        String title = book.getTitle() == null ? "" : book.getTitle();
        Log.d(TAG, title);
        String author = book.getAuthor() == null ? "" : book.getAuthor();
        Log.d(TAG, author);
        titleTV.setText(title);
        authorTV.setText(author);
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
        Intent homeIntent = new Intent(getActivity(), MainTabsActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        switch (item.getItemId()) {
            case R.id.delete_book:
                RegisterActivity.basePresenterActivity.deleteBook(book);
                break;
            case android.R.id.home:
                startActivity(homeIntent);
                break;
            case R.id.edit_this_book:
                goToEditBookForm(book);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void goToEditBookForm(Book book) {
        if (book == null) try {
            throw new Exception("Must bind book first");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = EditActivity.createEditIntent(getActivity(), book.getId(), book);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkout_book:
                callback.checkOut(book);
                break;
        }

    }

    public interface DetailInteractionListener {
        void checkOut(Book book);
    }

}
