package com.abasscodes.prolificlibrary.ui.onboard_welcome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.ui.onboard_welcome.genre_customizer.GenreViewHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by C4Q on 11/21/16.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreViewHolder>{
    private final List<String> items;
    private final Context context;
    private Set<String> selectedItems = new HashSet<>();

    public GenreAdapter(Context context, List<String> items){
        this.context = context;
       this.items = items;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenreViewHolder(parent, this);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        String item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Set<String> getSelectedItems() {
        return selectedItems;
    }
}
