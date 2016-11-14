package com.abasscodes.prolificlibrary.view.tab_fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;

/**
 * Created by C4Q on 11/11/16.
 */

public class AllBooksFragment extends AbstractTabRVFragment {
    public final static String TAG = "AllBooks";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.presenterActivity.fillOutNewBookForm();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}