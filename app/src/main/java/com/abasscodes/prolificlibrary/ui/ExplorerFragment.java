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
import com.abasscodes.prolificlibrary.model.nytimes.NYTClient;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.NYTResponse;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.Result;
import com.abasscodes.prolificlibrary.view.SuggestedBooksAdapter;

import java.util.List;

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
    private NYTClient client;
    @Bind(R.id.books_recycler_view) RecyclerView rv;
    private SuggestedBooksAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isAdded() && isVisible())
        getActivity().findViewById(R.id.fab).setVisibility(View.INVISIBLE);
        client = NYTClient.getInstance();

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
    }

    public void fetchBestSellers(){
        Call<NYTResponse> call = client.listBestSellers();
        call.enqueue(new Callback<NYTResponse>() {
            @Override
            public void onResponse(Call<NYTResponse> call, Response<NYTResponse> response) {
                List<Result> bookResults = response.body().getResults();
                adapter = new SuggestedBooksAdapter(bookResults);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NYTResponse> call, Throwable t) {
                Log.d(TAG, "Failure retrofitting " + t);
            }
        });
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
