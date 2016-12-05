package com.abasscodes.prolificlibrary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;

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

    public abstract Adapter getAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_tab, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                refreshContent();
                break;
        }
        return true;
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
        boolean emptyContent = recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() == 0;
        if (emptyContent) {
            showEmptyView();
        } else {
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
        ConnectionUtil.showConnecitonError(getView());
    }


    @Override
    public void onRefresh() {
        Log.d(TAG, "refresh");
        refreshContent();
    }


    public abstract void refreshContent();



}
