package com.abasscodes.prolificlibrary.user_interactions.explore_nyt_books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.abasscodes.prolificlibrary.view.RecyclerViewFragment;
import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.model.nytimes.NYTClient;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.NYTResponse;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.Result;
import com.abasscodes.prolificlibrary.view.adapters.SuggestedBooksAdapter;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/17/16.
 */

public class ExplorerFragment extends RecyclerViewFragment {
    private static final String TAG = ExplorerFragment.class.getSimpleName();
    private static ExplorerFragment instance;
    private SuggestedBooksAdapter adapter;
    public List<Result> results;

    public static Fragment getInstance() {
        if (instance == null) {
            instance = new ExplorerFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                llm.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshContent();
    }



    @Override
    public void refreshContent() {
        fetchBestSellers();
        fetchOptionalNYTFeed();
        swipeLayout.setRefreshing(false);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void fetchBestSellers() {
        Call<NYTResponse> call = NYTClient.getInstance().listBestSellers();
        call.enqueue(new Callback<NYTResponse>() {
            @Override
            public void onResponse(Call<NYTResponse> call, Response<NYTResponse> response) {
                if(response != null && response.body() != null) {
                    List<Result> bookResults = response.body().getResults();
                    if (adapter == null) {
                        adapter = new SuggestedBooksAdapter(bookResults);
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.addAll(bookResults);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<NYTResponse> call, Throwable t) {
                Log.d(TAG, "Failure retrofitting " + t);
            }
        });
    }

    public void fetchOptionalNYTFeed() {
        Set<String> nytSet = PreferenceHelper.getNYTFeeds(getActivity());
        Log.d(TAG, "nyt set size was " + nytSet.size());
        if (nytSet != null) {
            for (String category : nytSet) {
                Call<NYTResponse> call = NYTClient.getInstance().getCategoriesList(category);
                call.enqueue(new Callback<NYTResponse>() {
                    @Override
                    public void onResponse(Call<NYTResponse> call, Response<NYTResponse> response) {
                        NYTResponse nytResponse = response.body();
                        Log.d(TAG, "NYT api call size was " + nytResponse.getResults().size());
                        results = response.body().getResults();
                        if (results != null) {
                            if (adapter != null) {
                                adapter.addAll(response.body().getResults());
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NYTResponse> call, Throwable t) {
                        Log.d(TAG, "Failure " + t);
                    }
                });
            }
        }
    }


}
