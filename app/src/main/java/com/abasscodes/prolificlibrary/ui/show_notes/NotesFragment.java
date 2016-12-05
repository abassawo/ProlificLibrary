package com.abasscodes.prolificlibrary.ui.show_notes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

public class NotesFragment extends RecyclerViewFragment {

    public final static String TAG = "AllBooks";
    private static NotesFragment instance;
    private List<Book> books;

    public static NotesFragment newInstance(ArrayList<Book> books) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", books);
        instance = new NotesFragment();
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


    public static NotesFragment getInstance() {
        if (instance == null) {
            instance = new NotesFragment();
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
            adapter = new NotesAdapter(books);
        }else{
            adapter = new NotesAdapter();
        }
        return adapter;
    }


}