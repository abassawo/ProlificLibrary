package com.abasscodes.prolificlibrary.interactions.show_book_detail;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.AbstractPresenter;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

/**
 * Created by C4Q on 11/15/16.
 */

public class DetailPresenter extends AbstractPresenter implements Presenter {

    private static DetailPresenter instance;

    public static DetailPresenter getInstance(BasePresenterActivity activity) {
        if (instance == null) {
            instance = new DetailPresenter(activity);
        }
        return instance;
    }

    public DetailPresenter(BasePresenterActivity<AbstractPresenter> activity) {
        super(activity);
    }

    @Override
    public void showBookDetail(View view, Book book) {
        TextView title = (TextView) view.findViewById(R.id.book_title);
        TextView author = (TextView) view.findViewById(R.id.book_author);
        TextView pubTV = (TextView) view.findViewById(R.id.book_publisher);
        TextView checkOutStatusTV = (TextView) view.findViewById(R.id.book_checkout_status);
        if (book.getTitle() != null) {
            title.setText(book.getTitle());
        }
        if (book.getAuthor() != null) {
            author.setText(book.getAuthor());
        }
        if (book.getPublisher() != null) {
            pubTV.append(book.getPublisher());
        }
        if (book.getLastCheckedOut() != null) {
            checkOutStatusTV.append(book.getLastCheckedOut());
        }
    }

    @Override
    public void showCheckOutDialog(Book book) {

    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onConnectionFailure() {

    }
}
