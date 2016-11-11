package com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments;

/**
 * Created by C4Q on 11/11/16.
 */

public class ReadLaterFragment extends AbstractTabRVFragment {

    public static final String TAG = ReadLaterFragment.class.getSimpleName();

    public static CompletedBooksFragment newInstance() {
        return new CompletedBooksFragment();
    }

    @Override
    public void fetchBooks() {

    }
}
