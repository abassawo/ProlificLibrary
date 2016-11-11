package com.abasscodes.prolificlibrary.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    private Book book;

    public BookViewHolder(View itemView) {
        super(itemView);
        titleTV = (TextView) itemView.findViewById(R.id.book_item_title);
        authorTV = (TextView) itemView.findViewById(R.id.book_item_author);
        itemView.setOnClickListener(this);
        titleTV.setOnClickListener(this);
        titleTV.setOnClickListener(this);
        authorTV.setOnClickListener(this);
    }

    public void bindBook(Book book){
        this.book = book;
        this.titleTV.setText(book.getTitle());
        this.authorTV.setText(book.getAuthor());
    }


    @Override
    public void onClick(View v) {
        RegisterActivity.presenterActivity.showBookDetail(book);
    }
}
