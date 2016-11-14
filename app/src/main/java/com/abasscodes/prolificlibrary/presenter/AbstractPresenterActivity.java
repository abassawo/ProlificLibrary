package com.abasscodes.prolificlibrary.presenter;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.data.BookRepository;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.ui.detail.DetailActivity;
import com.abasscodes.prolificlibrary.ui.detail.DetailFragment;
import com.abasscodes.prolificlibrary.ui.editor.EditActivity;

/**
 * Created by C4Q on 11/11/16.
 */
public abstract class AbstractPresenterActivity extends AppCompatActivity implements Mvp.Presenter {

    public static final String BOOK_EXTRA_KEY = "Book";
    public static final String BOOK_TITLE = "Book_TItle";
    public Fragment currentFragment;



    @Override
    public void showBookDetail(Book book) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(BOOK_EXTRA_KEY, book.getId());
        intent.putExtra(BOOK_TITLE, book.getTitle());
        startActivity(intent);
    }


    @Override
    public void editBook(int id, Book book) {
        Intent intent = EditActivity.createEditIntent(this, book);
        startActivity(intent);
    }

    @Override
    public void fillOutNewBookForm() {
        Intent intent = EditActivity.fillOutNewBook(this);
        startActivity(intent);
    }

    @Override
    public void showNetworkSettings(){
        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    }




}

