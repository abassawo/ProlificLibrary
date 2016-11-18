package com.abasscodes.prolificlibrary.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.model.nytimes.pojos.Result;
import com.abasscodes.prolificlibrary.view.NYTBookViewHolder;

import java.util.List;

/**
 * Created by C4Q on 11/18/16.
 */
public class SuggestedBooksAdapter extends RecyclerView.Adapter<NYTBookViewHolder> {

    private final List<Result> results;

    public SuggestedBooksAdapter(List<Result> results){
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

    @Override
    public int getItemCount() {
        return results.size();
    }
}
