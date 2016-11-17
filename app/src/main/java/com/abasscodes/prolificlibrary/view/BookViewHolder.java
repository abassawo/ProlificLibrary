package com.abasscodes.prolificlibrary.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.interactions.checkout_book.CheckoutDialogFragment;
import com.abasscodes.prolificlibrary.interactions.show_all_books.TabPresenter;
import com.abasscodes.prolificlibrary.interactions.show_book_detail.DetailActivity;
import com.abasscodes.prolificlibrary.model.Book;

/**
 * Created by C4Q on 11/11/16.
 */

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private TextView titleTV;
    private TextView authorTV;
    private Book book;
    private ImageView checkedOutIV;

    public BookViewHolder(View itemView) {
        super(itemView);
        titleTV = (TextView) itemView.findViewById(R.id.book_item_title);
        authorTV = (TextView) itemView.findViewById(R.id.book_item_author);
        checkedOutIV = (ImageView) itemView.findViewById(R.id.checkout_icon_imgview);
        itemView.setOnLongClickListener(this);
        titleTV.setOnLongClickListener(this);
        itemView.setOnClickListener(this);
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

    @Override
    public boolean onLongClick(View v) {
            FragmentManager fm = RegisterActivity.basePresenterActivity.getSupportFragmentManager();
            CheckoutDialogFragment.newInstance(book).show(fm, null);
            return true;
    }
}
