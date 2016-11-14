package com.abasscodes.prolificlibrary.view;

import android.support.annotation.RawRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView titleTV;
    private TextView authorTV;
    private ImageView checkoutStatus;
    private Book book;

    public BookViewHolder(@RawRes int layoutRes, ViewGroup parent) {
        super(inflateView(layoutRes, parent));
        titleTV = (TextView) itemView.findViewById(R.id.book_item_title);
        authorTV = (TextView) itemView.findViewById(R.id.book_item_author);
        itemView.setOnClickListener(this);
        titleTV.setOnClickListener(this);
        titleTV.setOnClickListener(this);
        authorTV.setOnClickListener(this);
        checkoutStatus = (ImageView) itemView.findViewById(R.id.checkout_status_img);
    }

    public static View inflateView(int layoutRes, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(layoutRes, parent, false);
    }

    public void bindBook(Book book){
        this.book = book;
        this.titleTV.setText(book.getTitle());
        this.authorTV.setText(book.getAuthor());
        if(book.isCheckedOut()){
            checkoutStatus.setImageResource(R.drawable.checkout_true_img);
        }else{
            checkoutStatus.setImageResource(R.drawable.checkout_icon);
        }
    }


    @Override
    public void onClick(View v) {
        RegisterActivity.presenterActivity.showBookDetail(book);
    }
}
