package com.abasscodes.prolificlibrary.ui.show_book_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.MainTabsActivity;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.ui.checkout_book.CheckoutDialogFragment;
import com.abasscodes.prolificlibrary.ui.checkout_book.ReturnDialogFragment;
import com.abasscodes.prolificlibrary.ui.edit_book.EditActivity;

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
    @Bind(R.id.return_book)
    ImageView returnBook;
    private Book book;
    private APIClient client;
    private String TAG = "DetailFragment";

    //Extra for Detail Fragment Book AND for passing book onto Edit Activity
    public static final String BOOK_KEY = "book_detail";
    private DetailPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = APIClient.getInstance();
        presenter = DetailPresenter.getInstance((DetailActivity) getActivity());
        setRetainInstance(true);
        setHasOptionsMenu(true);
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
        if (book != null) {
            presenter.showBookDetail(getView(), book);
            if (book.isCheckedOut()) {
                checkoutBook.setVisibility(View.INVISIBLE);
                returnBook.setVisibility(View.VISIBLE);
                returnBook.setOnClickListener(this);
            } else {
                returnBook.setVisibility(View.INVISIBLE);
                checkoutBook.setVisibility(View.VISIBLE);
                checkoutBook.setOnClickListener(this);
            }
        } else {
            showError();
        }

    }

    private void showError() {
        //todo
    }

    public void bindBook(Book book) {
        this.book = book;
        if (book == null) return;
        String title = book.getTitle() == null ? "" : book.getTitle();
        Log.d(TAG, title);
        String author = book.getAuthor() == null ? "" : book.getAuthor();
        Log.d(TAG, author);
        titleTV.setText(title);
        authorTV.setText(author);
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


    public void showCheckoutDialog(final Book book) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        CheckoutDialogFragment.newInstance(book).show(fm, null);
    }

    public void showReturnDialog(Book book) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ReturnDialogFragment.newInstance(book).show(fm, null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkout_book:
                showCheckoutDialog(book);
                break;
            case R.id.return_book:
                showReturnDialog(book);

        }

    }


}