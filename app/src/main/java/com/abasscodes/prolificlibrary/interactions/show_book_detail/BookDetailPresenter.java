package com.abasscodes.prolificlibrary.interactions.show_book_detail;

import android.view.View;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.AbstractPresenter;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

/**
 * Created by C4Q on 11/15/16.
 */

public class BookDetailPresenter extends AbstractPresenter implements DetailPresenter {

    private static BookDetailPresenter instance;

    public static BookDetailPresenter getInstance(BasePresenterActivity activity){
        if(instance == null){
            return new BookDetailPresenter(activity);
        }
        return instance;
    }

    public BookDetailPresenter(BasePresenterActivity<AbstractPresenter> activity) {
        super(activity);
    }

    @Override
    public void showBookDetail(Book book) {
        TextView title = (TextView) activity.findViewById(R.id.book_title);
        TextView author = (TextView) activity.findViewById(R.id.book_author);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
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
