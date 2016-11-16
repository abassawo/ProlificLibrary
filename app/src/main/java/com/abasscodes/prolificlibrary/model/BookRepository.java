package com.abasscodes.prolificlibrary.model;

import android.util.Log;

import com.abasscodes.prolificlibrary.interactions.show_all_books.Presenter;
import com.abasscodes.prolificlibrary.interactions.show_all_books.TabPresenter;
import com.abasscodes.prolificlibrary.model.api.APIClient;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookRepository {

    private static final String TAG = BookRepository.class.getSimpleName();
    private static ArrayList<Book> allBooks = null;
    private TabPresenter presenter;


    private BookRepository(Presenter presenter) {
        this.presenter = (TabPresenter) presenter;
    }

    public BookRepository() {
        this((Presenter) RegisterActivity.basePresenterActivity.getPresenter());
    }


    public void fetchBooks() {

        final Call<ArrayList<Book>> call = APIClient.getInstance().getBooks();
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                allBooks = response.body();
                presenter.onAllBooksLoaded(allBooks);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Log.d(TAG, "failure  " + t);
                presenter.onConnectionFailure();
            }
        });

    }


}
