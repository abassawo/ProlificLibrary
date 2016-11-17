package com.abasscodes.prolificlibrary.view.tab_fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/11/16.
 */

public class AllBooksFragment extends BaseTabFragment implements BookRepository.BookCallback {

    public final static String TAG = "AllBooks";
    private static AllBooksFragment instance;
    private FragmentCommunication listener;

    public static AllBooksFragment getInstance(ArrayList<Book> books) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", books);
        instance = getInstance();
        instance.setArguments(args);
        return instance;
    }

    public static AllBooksFragment getInstance(){
        if (instance == null) {
            instance = new AllBooksFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        if(getArguments() == null) {
            new BookRepository(this).fetchBooks();
        }else{
            ArrayList<Book> books = getArguments().getParcelableArrayList("BOOKS");
            setupAdapter(books);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.fab).setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onBooksReady(ArrayList<Book> books) {
        if (isAdded()) {
            setupAdapter(books);
        }
        listener.setCheckedOutBooks(books);
    }


    @Override
    public void onDownloadFail() {
        //to-do error toast
        if (isAdded()) {
            setupAdapter(new ArrayList<Book>());
        }
    }

    //Used to transfer list of books that are currently checked out to host activity and fragment B
    public interface FragmentCommunication {
        void setCheckedOutBooks(ArrayList<Book> books);
    }
}