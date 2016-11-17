package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/11/16.
 */

public class CompletedBooksFragment extends BaseTabFragment {

    public static final String TAG =CompletedBooksFragment.class.getSimpleName();
    private static CompletedBooksFragment instance;

    public static CompletedBooksFragment newInstance(ArrayList<Book> books) {
        instance = new CompletedBooksFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", books);
        instance.setArguments(args);
        return new CompletedBooksFragment();
    }

    public static Fragment getInstance() {
        if(instance == null){
            instance = new CompletedBooksFragment();
        }
        return instance;
    }
}