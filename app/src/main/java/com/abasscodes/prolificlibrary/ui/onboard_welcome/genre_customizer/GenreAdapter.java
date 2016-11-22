package com.abasscodes.prolificlibrary.ui.onboard_welcome.genre_customizer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by C4Q on 11/21/16.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreViewHolder>{
    private final List<String> items;
    private final Context context;
//    private int resLayout;

//    public GenreAdapter(Context context, List<String> items, int resLayouut) {
//        this.resLayout = resLayouut;
//        this.context = context;
//        this.items = items;
//    }

    public GenreAdapter(Context context, List<String> items){
        this.context = context;
       this.items = items;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenreViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
