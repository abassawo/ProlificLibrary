package com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments;

import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.model.Book;

import java.util.List;

import retrofit2.Call;

/**
 * Created by C4Q on 11/11/16.
 */

public class CheckedOutBooksFragment extends AbstractTabRVFragment {
    public static String TAG = CheckedOutBooksFragment.class.getSimpleName();

    public static CheckedOutBooksFragment newInstance() {
        return new CheckedOutBooksFragment();
    }

    @Override
    public void fetchBooks() {
        Call<List<Book>> call = APIClient.getInstance().listCheckedOuttBooks();
    }
}