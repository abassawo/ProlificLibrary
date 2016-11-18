package com.abasscodes.prolificlibrary.model;

import android.util.Log;

import com.abasscodes.prolificlibrary.model.prolific.APIClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookRepository {

    private static final String TAG = BookRepository.class.getSimpleName();
    private BookCallback callback;


    public BookRepository(BookCallback callback) {
        this.callback = callback;


    }

    public void fetchBooks() {

        final Call<ArrayList<Book>> call = APIClient.getInstance().getBooks();
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                ArrayList<Book> books = response.body();
                callback.onBooksReady(books);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Log.d(TAG, "failure  " + t);
                callback.onDownloadFail();
            }
        });

    }


    public interface BookCallback {
        void onBooksReady(ArrayList<Book> books);
        void onDownloadFail();
    }
}
