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

public abstract class AbstractTabRVFragment extends Fragment{
    private static final String TAG = AbstractTabRVFragment.class.getSimpleName();
    private BookAdapter rvAdapter;
    @Bind(R.id.books_recycler_view)
    RecyclerView mRecyclerView;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(rvAdapter);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.presenterActivity.fillOutNewBookForm();
            }
        });

        fetchBooks();
    }

    public void fetchBooks(){
            final Call<ArrayList<Book>> call = APIClient.getInstance().getBooks();
            call.enqueue(new Callback<ArrayList<Book>>() {
                @Override
                public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                    if (response.body() == null)  {
                        Log.d(TAG, "Empty response");
                    }
                    else{
                        Log.d(TAG, response.body().toString());
                        List<Book> books = response.body();
                        setupAdapter(books);
                        Log.d(TAG,"books size " + books.size());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                    Log.d(TAG,"failure  " + t);
                }
            });



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