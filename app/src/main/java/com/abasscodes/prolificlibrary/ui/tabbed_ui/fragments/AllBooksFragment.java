package com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class AllBooksFragment extends AbstractTabRVFragment {
    public final static String TAG = AllBooksFragment.class.getSimpleName();

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        fetchBooks();
//}
//
//    public void fetchBooks(){
//        Call<List<Book>> call = APIClient.getInstance().getBooks();
//        call.enqueue(new Callback<List<Book>>() {
//            @Override
//            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
//                List<Book> books = response.body();
//                if(books != null) {
//                    setupAdapter(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Book>> call, Throwable t) {
//                Log.d(TAG, "failure " + t);
//            }
//        });
//
//    }


}