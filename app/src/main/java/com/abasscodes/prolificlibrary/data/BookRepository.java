package com.abasscodes.prolificlibrary.data;

import android.util.Log;

import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookRepository {

    private static final String TAG = BookRepository.class.getSimpleName();
    private static ArrayList<Book> allBooks = null;
    private Presenter presenter;
    public boolean isFetching = false;

    private BookRepository(Presenter presenter) {
        this.presenter = presenter;
    }

    public BookRepository() {
        this(RegisterActivity.presenterActivity);
    }


    public void fetchBooks() {
        if (isFetching) {
            Log.d(TAG, "Currently fetching");
        } else {
            isFetching = true;
            final Call<ArrayList<Book>> call = APIClient.getInstance().getBooks();
            call.enqueue(new Callback<ArrayList<Book>>() {
                @Override
                public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                    isFetching = false;
                    allBooks = response.body();
                    presenter.onAllBooksLoaded(allBooks);
                }

                @Override
                public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                    isFetching = false;
                    Log.d(TAG, "failure  " + t);
                    presenter.onConnectionFailure();
                }
            });
        }
    }

    public boolean isFetching() {
        return isFetching;
    }
}
