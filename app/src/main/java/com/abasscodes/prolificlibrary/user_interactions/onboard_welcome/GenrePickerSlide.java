package com.abasscodes.prolificlibrary.user_interactions.onboard_welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import java.util.Arrays;
import java.util.Set;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/21/16.
 */

public class GenrePickerSlide extends BaseSlideFragment {
    private static final String TAG = GenrePickerSlide.class.getSimpleName();
    @Bind(R.id.genre_recycler_view) RecyclerView recyclerView;
    private GenreAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_genre_chooser, container, false);
        ButterKnife.bind(this, view);
        setupRV();
        return view;
    }

    @Override
    public boolean canMoveFurther() {
        return true;
    }

    @Override
    public void onPause() {
        Set<String> nytFeed = adapter.getSelectedItems();
        PreferenceHelper.saveNYTFeed(getActivity(), nytFeed);
        super.onPause();
    }

    public void setupRV() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        String[] genreArr = getResources().getStringArray(R.array.genres);
        adapter = new GenreAdapter(getActivity(), Arrays.asList(genreArr));
        recyclerView.setAdapter(adapter);
    }


}