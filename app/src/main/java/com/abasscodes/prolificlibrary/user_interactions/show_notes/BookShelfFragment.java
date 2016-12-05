package com.abasscodes.prolificlibrary.user_interactions.show_notes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.view.RecyclerViewFragment;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

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
        if(isVisible()) {
            if (getAdapter() == null || getAdapter().getItemCount() == 0)
                emptyView.setVisibility(View.VISIBLE);
        }
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
        showRecyclerView();
        swipeLayout.setRefreshing(false);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        if(adapter != null) return adapter;
        if (getArguments() != null) {
            books = getArguments().getParcelableArrayList("BOOKS");
            adapter = new OuterShelfAdapter(books);
        }else{
            adapter = new OuterShelfAdapter();
        }
        return adapter;
    }


}