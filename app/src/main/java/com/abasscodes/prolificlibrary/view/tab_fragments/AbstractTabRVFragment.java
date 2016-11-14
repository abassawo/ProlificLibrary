package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.view.BookAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/11/16.
 */

public abstract class AbstractTabRVFragment extends Fragment{
    private static final String TAG = AbstractTabRVFragment.class.getSimpleName();
    private BookAdapter rvAdapter;
    @Bind(R.id.books_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.empty_view)
    View emptyView;
    private List<Book> books;


    public static AbstractTabRVFragment newInstance(ArrayList<Book> books){
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", books); //fixme
        AbstractTabRVFragment fragment = new AllBooksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
//        fetchBooks();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle args = getArguments();
        if (args != null) {
            books = args.getParcelableArrayList("BOOKS");
        }
        rvAdapter = new BookAdapter(books);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_books, container, false);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        fab.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(rvAdapter);
        setupAdapter(books);
    }



    private List<Book> filter(List<Book> books, String query){
        query = query.toLowerCase();
        final List<Book> filteredResults = new ArrayList<>();
        for (Book book : books) {
            final String text = book.getTitle().toLowerCase();
            if(text.startsWith(query) || text.contains(query)) {
                filteredResults.add(book);
            }
        }
        return filteredResults;

    }



    protected void setupAdapter(List<Book> books){
        Log.d(TAG, "Payload size " + books.size());
        rvAdapter.setBooks(books);
        rvAdapter.notifyDataSetChanged();
        if(rvAdapter.getItemCount() == 0){
            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.INVISIBLE);
        }
        mRecyclerView.setAdapter(rvAdapter);
        mRecyclerView.scrollToPosition(0);

    }


}