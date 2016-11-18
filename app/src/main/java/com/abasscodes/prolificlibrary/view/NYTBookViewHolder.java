package com.abasscodes.prolificlibrary.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.Result;

/**
 * Created by C4Q on 11/18/16.
 */
public class NYTBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView titleTV;
    private TextView authorTV;
    private Result book;
    private ImageView checkedOutIV;

    public NYTBookViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        titleTV = (TextView) itemView.findViewById(R.id.book_item_title);
        authorTV = (TextView) itemView.findViewById(R.id.book_item_author);
        checkedOutIV = (ImageView) itemView.findViewById(R.id.checkout_icon_imgview);
        itemView.setOnClickListener(this);
    }

    public static View inflateView(ViewGroup parent){
       LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.book_item, parent, false);
    }

    public void bindBook(Result result) {
        this.titleTV.setText(result.getTitle());
        this.authorTV.setText(result.getAuthor());
    }


    @Override
    public void onClick(View v) {

    }


}
