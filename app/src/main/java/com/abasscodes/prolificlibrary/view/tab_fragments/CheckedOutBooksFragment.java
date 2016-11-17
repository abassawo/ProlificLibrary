package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.api.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class CheckedOutBooksFragment extends BaseTabFragment {
    public static String TAG = CheckedOutBooksFragment.class.getSimpleName();
    private static CheckedOutBooksFragment instance;
    private ArrayList<Book> books;

    public static CheckedOutBooksFragment newInstance() {
        return new CheckedOutBooksFragment();
    }

    public static CheckedOutBooksFragment getInstance(ArrayList<Book> books) {
        instance = new CheckedOutBooksFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", books);
        instance.setArguments(args);
        return instance;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            books = getArguments().getParcelableArrayList("BOOKS");
            if (books != null) {
                setupAdapter(books);
            }
        }
    }

    @Override
    public void refresh(ArrayList<Book> books) {
        super.refresh(books);
    }

    //        else
//        {
//            Call<List<Book>> call = APIClient.getInstance().getCheckedOutBooks();
//            call.enqueue(new Callback<List<Book>>() {
//                @Override
//                public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
//                    books = (ArrayList<Book>) response.body();
//                    setupAdapter(books);
//                }
//
//                @Override
//                public void onFailure(Call<List<Book>> call, Throwable t) {
//                    Log.d(TAG, "Error retrofitting" + t);
//                }
//            });
//
//
//        }
//    }
}