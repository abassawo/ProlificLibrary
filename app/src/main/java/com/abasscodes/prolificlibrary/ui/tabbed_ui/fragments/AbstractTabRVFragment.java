package com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments;

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
import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.ui.tabbed_ui.BookAdapter;

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

public class AbstractTabRVFragment extends Fragment implements Callback<List<Book>> {
    private static final String TAG = AbstractTabRVFragment.class.getSimpleName();

    private static String TAB_TYPE_TAG = "tab_tag_filter";
    protected boolean checkedOutOnly = false;
    public APIClient client;
    private BookAdapter rvAdapter;
    @Bind(R.id.books_recycler_view)
    RecyclerView recyclerView;
    private static AbstractTabRVFragment Instance;
    @Bind(R.id.empty_view)
    View emptyView;



    @Override
    public void onResume() {
        super.onResume();
        fetchBooks();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        rvAdapter = new BookAdapter(getActivity());
        fetchBooks();
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
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.presenterActivity.fillOutNewBookForm();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchBooks();
    }

    public void fetchBooks(){
        Call<List<Book>> call = APIClient.getInstance().getBooks();
        call.enqueue(this);
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
        rvAdapter.setBooks(books);
        rvAdapter.notifyDataSetChanged();
        if(rvAdapter.getItemCount() == 0){
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setAdapter(rvAdapter);
            recyclerView.scrollToPosition(0);
        }

    }

    @Override
    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
        setupAdapter(response.body());
    }

    @Override
    public void onFailure(Call<List<Book>> call, Throwable t) {
        Log.d(TAG, "Failure " + t);
    }




}