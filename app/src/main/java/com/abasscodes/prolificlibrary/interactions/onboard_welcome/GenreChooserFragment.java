package com.abasscodes.prolificlibrary.interactions.onboard_welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.abasscodes.prolificlibrary.R;

import java.util.Arrays;
import java.util.List;

import agency.tango.materialintroscreen.SlideFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/21/16.
 */

public class GenreChooserFragment extends SlideFragment implements View.OnClickListener {
    private boolean canMoveFurther = false;
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
    public int backgroundColor() {
        return R.color.colorPrimary;
    }


    @Override
    public int buttonsColor() {
        return R.color.colorAccent;
    }

    @Override
    public boolean canMoveFurther() {
        return false;
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        return getString(R.string.error_message);
    }

    @Override
    public void onClick(View v) {
        canMoveFurther = true;
    }

    public void setupRV(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        String[] genreArr = getResources().getStringArray(R.array.genres);
        recyclerView.setAdapter(new GenreAdapter(getActivity(), Arrays.asList(genreArr)));
    }
}