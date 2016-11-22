package com.abasscodes.prolificlibrary.ui.onboard_welcome.genre_customizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.ui.onboard_welcome.BaseSlideFragment;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/21/16.
 */

public class GenrePickerSlide extends BaseSlideFragment {
    @Bind(R.id.genre_recycler_view) RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_genre_chooser, container, false);
        ButterKnife.bind(this, view);
        setupRV(recyclerView);
        return view;
    }

    @Override
    public boolean canMoveFurther() {
        return true;
    }



    public void setupRV(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        String[] genreArr = getResources().getStringArray(R.array.genres);
        recyclerView.setAdapter(new GenreAdapter(getActivity(), Arrays.asList(genreArr)));
    }


}