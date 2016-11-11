package com.abasscodes.prolificlibrary.data;

import android.util.Log;

import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.Presenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookRepository {

    private static final String TAG = BookRepository.class.getSimpleName() ;

    public static void fetchBooks(){
        final Call<ArrayList<Book>> call = APIClient.getInstance().getBooks();
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                Presenter presenter = RegisterActivity.presenterActivity;
                ArrayList<Book> books = response.body();
                presenter.onAllBooksLoaded(books);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Log.d(TAG,"failure  " + t);
            }
        });

    }
}
