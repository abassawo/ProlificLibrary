package com.abasscodes.prolificlibrary.user_interactions.show_book_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.MainTabsActivity;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.idreambooks.DreamApiClient;
import com.abasscodes.prolificlibrary.model.idreambooks.pojos.CriticReview;
import com.abasscodes.prolificlibrary.model.idreambooks.pojos.ReviewResponse;
import com.abasscodes.prolificlibrary.user_interactions.checkout_book.CheckoutDialogFragment;
import com.abasscodes.prolificlibrary.user_interactions.checkout_book.ReturnDialogFragment;
import com.abasscodes.prolificlibrary.user_interactions.edit_book.EditActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by C4Q on 11/11/16.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.book_title)
    TextView titleTV;
    @Bind(R.id.book_author)
    TextView authorTV;
    @Bind(R.id.checkout_book)
    Button checkoutBook;
    @Bind(R.id.return_book)
    Button returnBook;
    private Book book;
    @Bind(R.id.book_checkout_status)
    TextView lastCheckedOutTV;
    @Bind(R.id.books_recycler_view)
    RecyclerView criticsRV;

    private String TAG = "DetailFragment";

    //Extra for Detail Fragment Book AND for passing book onto Edit Activity
    public static final String BOOK_KEY = "book_detail";
    private DetailPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                lastCheckedOutTV.setVisibility(View.INVISIBLE);
                returnBook.setVisibility(View.INVISIBLE);
                checkoutBook.setVisibility(View.VISIBLE);
                checkoutBook.setOnClickListener(this);

            }
        } else {
            showError();
        }

    }

    public void showBookReviews(Book book){
        Call<ReviewResponse> call = DreamApiClient.getInstance().getBookReview(book.getTitle());

    }

    private void showError() {
        //todo
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
