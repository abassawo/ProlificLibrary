package com.abasscodes.prolificlibrary.interactions.onboard_welcome;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class GenreChooserFragment extends BaseSlideFragment{
    @Bind(R.id.genre_recycler_view) RecyclerView recyclerView;
    private Callback listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener.allowMainContentTransition(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            this.listener = (Callback) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException("Host Activity must implement Callback");
        }
    }


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

    public interface Callback{
       void allowMainContentTransition(boolean allow);
    }
}