package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/11/16.
 */

public class ReadLaterFragment extends BaseTabFragment {

    public static final String TAG = ReadLaterFragment.class.getSimpleName();
    private static ReadLaterFragment instance;

    public static ReadLaterFragment newInstance(ArrayList<Book> archivedBooks) {
        instance = new ReadLaterFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("BOOKS", archivedBooks);
        instance.setArguments(args);
        return new ReadLaterFragment();
    }

    public static Fragment getInstance() {
        if(instance == null){
            instance = new ReadLaterFragment();
        }
        return instance;
    }
}
