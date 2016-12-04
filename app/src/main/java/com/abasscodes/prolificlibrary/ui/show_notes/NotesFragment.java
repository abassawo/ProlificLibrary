package com.abasscodes.prolificlibrary.ui.show_notes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.RecyclerViewFragment;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.model.database.BookContentProvider;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class NotesFragment extends RecyclerViewFragment {

    public final static String TAG = "AllBooks";
    private static NotesFragment instance;
    private NotesAdapter rvAdapter;
    private List<Book> books;

    public static NotesFragment newInstance(ArrayList<Book> books) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", books);
        instance = new NotesFragment();
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setBackgroundResource(R.drawable.shelf);
    }

    public static NotesFragment getInstance() {
        if (instance == null) {
            instance = new NotesFragment();
        }
        return instance;
    }

    @Override
    public void refreshContent() {
        if (isAdded()) {
            if (rvAdapter.getItemCount() == 0) {
                showEmptyView();
            } else {
                showRecyclerView();
            }
        }
        swipeLayout.setRefreshing(false);

    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        if (rvAdapter != null) return rvAdapter;
        if (getArguments() != null) {
            books = getArguments().getParcelableArrayList("BOOKS");
            rvAdapter = new NotesAdapter(books);
        }
        return rvAdapter;
    }


}