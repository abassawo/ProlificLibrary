package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.view.BookAdapter;

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

public abstract class BaseTabFragment extends Fragment{
    private static final String TAG = BaseTabFragment.class.getSimpleName();
    public BookAdapter rvAdapter;
    @Bind(R.id.books_recycler_view)
    RecyclerView bookRecyclerView;
    private static BaseTabFragment Instance;
    @Bind(R.id.empty_view) View emptyView;
    private List<Book> books;


    public void refresh(ArrayList<Book> books){
        this.books = books;
        if(isAdded() && isVisible()){
            setupAdapter(books);
        }

    }

    public void refresh(){
        Call<ArrayList<Book>> call = APIClient.getInstance().getBooks();
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                books = response.body();
                setupAdapter(books);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        if(isAdded() && rvAdapter != null) {
//            refresh();
//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        Bundle args = getArguments();
        if (args != null) {
            books = args.getParcelableArrayList("BOOKS");
        }
        rvAdapter = new BookAdapter(getActivity(), books);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_recycler_view, container, false);
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
        if(rvAdapter == null) rvAdapter = new BookAdapter(getActivity(),books);
        else {
            rvAdapter.setBooks(books);
            rvAdapter.notifyDataSetChanged();
        }
        if(bookRecyclerView != null) {
            if (rvAdapter.getItemCount() == 0) {
                bookRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                bookRecyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.INVISIBLE);
            }
            bookRecyclerView.setAdapter(rvAdapter);
            bookRecyclerView.scrollToPosition(0);
        }

    }


}