package com.abasscodes.prolificlibrary.user_interactions.show_book_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.MainTabsActivity;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;
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
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */
public class DetailFragment extends Fragment{

    @Bind(R.id.book_title)
    TextView titleTV;
    @Bind(R.id.book_author)
    TextView authorTV;

    private Book book;
    @Bind(R.id.book_checkout_status)
    TextView lastCheckedOutTV;
    @Bind(R.id.detail_recycler_view)
    RecyclerView criticsRV;
    @Bind(R.id.rating_bar)
    RatingBar ratingBar;

    private String TAG = "DetailFragment";
    private ReviewAdapter reviewAdapter;

    //Extra for Detail Fragment Book AND for passing book onto Edit Activity
    public static final String BOOK_KEY = "book_detail";
    private DetailPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        presenter = DetailPresenter.getInstance((DetailActivity) getActivity());
        reviewAdapter = new ReviewAdapter();
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
        book = getArguments().getParcelable(BOOK_KEY);
        ButterKnife.bind(this, view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        criticsRV.setLayoutManager(llm);
        criticsRV.setAdapter(reviewAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                llm.getOrientation());
        criticsRV.addItemDecoration(dividerItemDecoration);
        if(!book.isCheckedOut()) lastCheckedOutTV.setVisibility(View.INVISIBLE);
        showBookReviews(book);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (book != null) {
            presenter.showBookDetail(getView(), book);
        }

    }

    public void showBookReviews(final Book book) {
        Call<ReviewResponse> call = DreamApiClient.getInstance(getActivity()).getBookReview(book.getTitle().toLowerCase());
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                Log.d(TAG, "For book " + book.getTitle() + response.isSuccessful() + " items were " + response.body().getTotalResults());
                if (response != null) {
                    ReviewResponse reviewBody = response.body();
                    if (reviewBody != null) {
                        com.abasscodes.prolificlibrary.model.idreambooks.pojos.Book book = reviewBody.getBook();
                        List<CriticReview> reviews = reviewBody.getBook().getCriticReviews();
                        reviewAdapter.setReviews(reviews);
                        if (book.getAverageCriticReviews() > 0) {
                            ratingBar.setVisibility(View.VISIBLE);
                            ratingBar.setRating(book.getAverageCriticReviews());
                        }
                    }
                }

                ((DetailActivity) getActivity()).minimizeToolbar();
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Log.d(TAG, "Failure retrofitting " + t);
                ((DetailActivity) getActivity()).minimizeToolbar();
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






    public interface DetailFragmentListener{
        void checkout(Book book);
        void returnBook(Book book);
    }


}
