package com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments;

import android.util.Log;

import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class CompletedBooksFragment extends AbstractTabRVFragment {

    public static final String TAG =CompletedBooksFragment.class.getSimpleName();

    public static CompletedBooksFragment newInstance() {
        return new CompletedBooksFragment();
    }

}