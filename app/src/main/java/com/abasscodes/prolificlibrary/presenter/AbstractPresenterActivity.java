package com.abasscodes.prolificlibrary.presenter;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.ui.detail.DetailActivity;
import com.abasscodes.prolificlibrary.ui.detail.DetailFragment;
import com.abasscodes.prolificlibrary.ui.editor.EditActivity;

/**
 * Created by C4Q on 11/11/16.
 */
public abstract class AbstractPresenterActivity extends AppCompatActivity implements Presenter{

    public static final String BOOK_EXTRA_KEY = "Book";
    public static final String BOOK_TITLE = "Book_TItle";
    public Fragment currentFragment;

    abstract void hostFragmentInTab(TabAdapter.TabType type);


    @Override
    public void showAllBooks() {
        hostFragmentInTab(TabAdapter.TabType.AllBooksTab);
    }

    @Override
    public void showBookDetail(Book book) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(BOOK_EXTRA_KEY, book.getId());
        intent.putExtra(BOOK_TITLE, book.getTitle());
        startActivity(intent);
    }

    @Override
    public void editBook(int id, String title, String author, String pub, String tags) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(DetailFragment.BOOK_ARG_ID, id);   //Send original id to the hosting activity.
        intent.putExtra(DetailFragment.BOOK_AUTHOR, author);   //Send original id to the hosting activity.
        intent.putExtra(DetailFragment.BOOK_TITLE, title);   //Send original id to the hosting activity.
        intent.putExtra(DetailFragment.BOOK_TAGS, tags);   //Send original id to the hosting activity.
        intent.putExtra(DetailFragment.BOOK_PUBS, pub);   //Send original id to the hosting activity.
        startActivity(intent);
    }

    @Override
    public void fillOutNewBookForm() {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }





}

