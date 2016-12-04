package com.abasscodes.prolificlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.List;

/**
 * Created by C4Q on 11/21/16.
 */

public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{
    private List<T> items;
    private Context context;
    private int rowLayout;

    public BaseRecyclerAdapter(Context context, List<T> items, int resLayout) {
        this.context = context;
        this.items = items;
        this.rowLayout = resLayout;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(parent, rowLayout);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        if (items == null) return 0;
        else return items.size();
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}



