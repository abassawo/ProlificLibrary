package com.abasscodes.prolificlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.view.BookAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.Adapter;

/**
 * Created by C4Q on 12/3/16.
 */

public abstract class RecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = RecyclerViewFragment.class.getSimpleName();
    @Bind(R.id.books_recycler_view)
    protected RecyclerView recyclerView;
    @Bind(R.id.empty_view)
    protected View emptyView;
    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeLayout;
    protected Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_recycler_view, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeLayout.setOnRefreshListener(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showRecyclerView();
        recyclerView.setAdapter(getAdapter());
        if(recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() == 0) {
            showEmptyView();
        }else{
            showRecyclerView();
        }

    }

    public void showRecyclerView() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.INVISIBLE);
    }

    public void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        RegisterActivity.basePresenterActivity.showConnectionError();
    }


    @Override
    public void onRefresh() {
        Log.d(TAG, "refresh");
        refreshContent();
    }


    public abstract void refreshContent();


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_tab, menu);
    }

    public abstract Adapter getAdapter();
}
