package com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments;

import android.util.Log;

import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class CompletedBooksFragment extends AbstractTabRVFragment {

    public static final String TAG =CompletedBooksFragment.class.getSimpleName();

    public static CompletedBooksFragment newInstance() {
        return new CompletedBooksFragment();
    }

    @Override
    public void fetchBooks() {
        Call<List<Book>> call = APIClient.getInstance().getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> books = new ArrayList();
                if (books != null && books.size() > 0) {


                    for (Book book : response.body()) {
                        if (!book.isCompleted) {
                            books.remove(book);
                        }
                    }
                    setupAdapter(books);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d(TAG, "error " + t);
            }
        });
    }
}