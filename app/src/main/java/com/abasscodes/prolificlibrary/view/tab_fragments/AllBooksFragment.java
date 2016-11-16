package com.abasscodes.prolificlibrary.view.tab_fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.abasscodes.prolificlibrary.R;

/**
 * Created by C4Q on 11/11/16.
 */

public class AllBooksFragment extends AbstractTabRVFragment {
    public final static String TAG = "AllBooks";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.fab).setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }





}