package com.abasscodes.prolificlibrary.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.model.nytimes.NYTClient;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.NYTResponse;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.Result;
import com.abasscodes.prolificlibrary.view.SuggestedBooksAdapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/17/16.
 */

public class ExplorerFragment extends Fragment {
    private static final String TAG = ExplorerFragment.class.getSimpleName();
    private static ExplorerFragment instance;
    @Bind(R.id.books_recycler_view) RecyclerView rv;
    private SuggestedBooksAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchBestSellers();
        fetchOptionalNYTFeed();
    }

    public void fetchBestSellers(){
        Call<NYTResponse> call = NYTClient.getInstance().listBestSellers();
        call.enqueue(new Callback<NYTResponse>() {
            @Override
            public void onResponse(Call<NYTResponse> call, Response<NYTResponse> response) {
                List<Result> bookResults = response.body().getResults();
                if(adapter == null) {
                    adapter = new SuggestedBooksAdapter(bookResults);
                    rv.setAdapter(adapter);
                }else {
                    adapter.addAll(bookResults);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NYTResponse> call, Throwable t) {
                Log.d(TAG, "Failure retrofitting " + t);
            }
        });
    }

    public void fetchOptionalNYTFeed(){
        Set<String> nytSet =  PreferenceHelper.getNYTFeeds(getActivity());
        Log.d(TAG, "nyt set size was " + nytSet.size());
        if(nytSet != null) {
            for (String category : nytSet) {
                Call<NYTResponse> call = NYTClient.getInstance().getCategoriesList(category);
                call.enqueue(new Callback<NYTResponse>() {
                    @Override
                    public void onResponse(Call<NYTResponse> call, Response<NYTResponse> response) {
                        NYTResponse nytResponse = response.body();
                        Log.d(TAG, "NYT api call size was " + nytResponse.getResults().size());
                        List<Result> results = response.body().getResults();
                        if(results != null) {
                            if(adapter != null){
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

    @Override
    public void onResume() {
        super.onResume();
    }

    public static Fragment getInstance() {
        if(instance == null){
            instance = new ExplorerFragment();
        }
        return instance;
    }
}
