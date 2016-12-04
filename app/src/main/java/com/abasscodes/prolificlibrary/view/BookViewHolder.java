package com.abasscodes.prolificlibrary.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.ui.show_book_detail.DetailActivity;
import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView titleTV;
    private TextView authorTV;
    private Book book;
    private ImageView checkedOutIV;

    public BookViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        titleTV = (TextView) itemView.findViewById(R.id.book_item_title);
        authorTV = (TextView) itemView.findViewById(R.id.book_item_author);
        checkedOutIV = (ImageView) itemView.findViewById(R.id.checkout_icon_imgview);
        itemView.setOnClickListener(this);
    }

    public static View inflateView(ViewGroup parent) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.book_item, parent, false);
    }

    public void bindBook(Book book) {
        this.book = book;
        this.titleTV.setText(book.getTitle());
        this.authorTV.setText(book.getAuthor());
        if (book.isCheckedOut()) {
            checkedOutIV.setImageResource(R.drawable.checkout_status_on);
        }
    }


    @Override
    public void onClick(View v) {
        Context ctx = v.getContext();
        Intent intent = DetailActivity.makeIntent(ctx, book);
        ctx.startActivity(intent);
    }

//    @Override
//    public boolean onLongClick(View v) {
//            FragmentManager fm = RegisterActivity.basePresenterActivity.getSupportFragmentManager();
//            CheckoutDialogFragment.newInstance(book).show(fm, null);
//            return true;
//    }
}
