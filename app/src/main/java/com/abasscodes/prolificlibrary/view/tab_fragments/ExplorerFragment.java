package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.support.v4.app.Fragment;

/**
 * Created by C4Q on 11/17/16.
 */

public class ExplorerFragment extends Fragment {
    private static ExplorerFragment instance;

    public static Fragment getInstance() {
        if(instance == null){
            instance = new ExplorerFragment();
        }
        return instance;
    }
}
