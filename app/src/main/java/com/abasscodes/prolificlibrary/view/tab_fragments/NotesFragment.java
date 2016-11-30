package com.abasscodes.prolificlibrary.view.tab_fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.view.BookAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/11/16.
 */

public class NotesFragment extends Fragment implements BookRepository.BookCallback {

    public final static String TAG = "AllBooks";


    public NotesAdapter rvAdapter;
    @Bind(R.id.books_recycler_view)
    RecyclerView notesRecyclerView;
    private static NotesFragment instance;
    @Bind(R.id.empty_view) View emptyView;
    private List<Book> books;

    public static NotesFragment newInstance(ArrayList<Book> books) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", books);
        instance =new NotesFragment();
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }


    public static NotesFragment getInstance(){
        if (instance == null) {
            instance = new NotesFragment();
        }
        return instance;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        notesRecyclerView.setAdapter(rvAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

//    protected void setupAdapter(List<Book> books){
//        Log.d(TAG, "Payload size " + books.size());
//        if(rvAdapter == null) rvAdapter = new BookAdapter(getActivity(),books);
//        else {
//            rvAdapter.setBooks(books);
//            rvAdapter.notifyDataSetChanged();
//        }
//        if(bookRecyclerView != null) {
//            if (rvAdapter.getItemCount() == 0) {
//                bookRecyclerView.setVisibility(View.GONE);
//                emptyView.setVisibility(View.VISIBLE);
//            } else {
//                bookRecyclerView.setVisibility(View.VISIBLE);
//                emptyView.setVisibility(View.INVISIBLE);
//            }
//            bookRecyclerView.setAdapter(rvAdapter);
//            bookRecyclerView.scrollToPosition(0);
//        }
//
//    }
    @Override
    public void onBooksReady(ArrayList<Book> books) {
        if (isAdded() && isVisible()) {
//            setupAdapter(books);
        }
//        listener.setCheckedOutBooks(books);
    }


    @Override
    public void onDownloadFail() {

    }

}