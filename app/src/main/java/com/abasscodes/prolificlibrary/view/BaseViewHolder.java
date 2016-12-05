package com.abasscodes.prolificlibrary.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by C4Q on 11/21/16.
 */
public class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    T item;


    public BaseViewHolder(ViewGroup parent, Integer rowLayout){
        super(inflateView(parent, rowLayout));
    }

    private static View inflateView(ViewGroup parent, int rowLayout) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(rowLayout, parent, false);
    }

    public void bind(T item){
        this.item = item;
    }
}
