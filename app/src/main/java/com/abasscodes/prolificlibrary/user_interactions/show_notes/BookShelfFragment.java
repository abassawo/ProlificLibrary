package com.abasscodes.prolificlibrary.user_interactions.show_notes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.view.RecyclerViewFragment;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookShelfFragment extends RecyclerViewFragment {

    public final static String TAG = "AllBooks";
    private static BookShelfFragment instance;
    private List<Book> books;


    public static BookShelfFragment newInstance(ArrayList<Book> books) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", books);
        instance = new BookShelfFragment();
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isVisible()) {
            if (getAdapter() == null || getAdapter().getItemCount() == 0)
                emptyView.setVisibility(View.VISIBLE);
        }
        refreshContent();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setBackgroundResource(R.drawable.shelf);
        showRecyclerView();
    }


    public static BookShelfFragment getInstance() {
        if (instance == null) {
            instance = new BookShelfFragment();
        }
        return instance;
    }


    @Override
    public void refreshContent() {
        getCheckedOutBooks();
        showRecyclerView();
        swipeLayout.setRefreshing(false);
    }

    public void getCheckedOutBooks() {
        final List<Book> checkedOut = new ArrayList<>();
        Call call = APIClient.getInstance().getBooks();
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                for (Book book : response.body()) {
                    if (book.isCheckedOut()) checkedOut.add(book);
                }
                ((OuterShelfAdapter) adapter).setBooks(checkedOut);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

            }
        });
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        if (adapter != null) return adapter;
        if (getArguments() != null) {
            books = getArguments().getParcelableArrayList("BOOKS");
            adapter = new OuterShelfAdapter(books);
        } else {
            adapter = new OuterShelfAdapter();
        }
        return adapter;
    }


}