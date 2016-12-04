package com.abasscodes.prolificlibrary.ui.show_all_books;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.view.RecyclerViewFragment;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;
import com.abasscodes.prolificlibrary.helpers.TextUtilHelper;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.view.BookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 */

public class AllBooksFragment extends RecyclerViewFragment implements BookRepository.BookCallback {

    public final static String TAG = "AllBooks";
    private FragmentCommunication listener;
    private List<Book> books;
    private BookAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                String temp = "";
                if (query.length() > temp.length()) {
                    final List<Book> filteredBooks = TextUtilHelper.filter(books, query);
                    adapter.setBooks(filteredBooks);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(0);
                } else if (query.length() <= 0) {
                    adapter.setBooks(books);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(0);
                }
                return false;
            }
        });
    }



    @Override
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (FragmentCommunication) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString()
                    + " must implement Fragment Communication");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshContent();
    }



    @Override
    public void refreshContent() {
        if (ConnectionUtil.isConnected()) {
            new BookRepository(this).fetchBooks();
        } else {
            Snackbar.make(getView(), "No Internet", Snackbar.LENGTH_SHORT).show();
        }
        if(swipeLayout != null)
        swipeLayout.setRefreshing(false);
    }


    @Override
    public void onBooksReady(ArrayList<Book> books) {
        this.books = books;
        if (adapter == null) {
            adapter = new BookAdapter(getActivity(),books);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setBooks(books);
        }
        if(adapter.getItemCount() == 0) showEmptyView();
        ArrayList<Book> myBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isCheckedOut()) myBooks.add(book);
        }
        listener.setCheckedOutBooks(myBooks);
    }

    @Override
    public void onDownloadFail() {
        if (isAdded()) {
            ConnectionUtil.showConnecitonError();
        }
    }

    //Used to transfer list of books that are currently checked out to host activity and fragment B
    public interface FragmentCommunication {
        void setCheckedOutBooks(ArrayList<Book> books);
    }
}