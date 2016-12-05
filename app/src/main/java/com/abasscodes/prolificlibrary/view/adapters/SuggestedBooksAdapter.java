package com.abasscodes.prolificlibrary.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.model.nytimes.pojos.Result;
import com.abasscodes.prolificlibrary.user_interactions.explore_nyt_books.NYTBookViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/18/16.
 */
public class SuggestedBooksAdapter extends RecyclerView.Adapter<NYTBookViewHolder> {

    private List<Result> results;

    public SuggestedBooksAdapter() {
        this.results = new ArrayList<>();
    }

    public SuggestedBooksAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public NYTBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NYTBookViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(NYTBookViewHolder holder, int position) {
        Result result = results.get(position);
        holder.bindBook(result);
    }

    public List<Result> getData() {
        return results;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public void addAll(List<Result> results) {
        if(this.results == null){
            this.results = results;
        }else{
            results.addAll(results);
        }
    }
}