package com.abasscodes.prolificlibrary.ui.edit_book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

/**
 * Created by C4Q on 11/11/16.
 */
public class EditActivity extends AppCompatActivity {

    private static final String BOOK_KEY = "book_key";
    private static final String NEW_BOOK = "new_book_bool";
    private ActionBar actionBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        setupToolbar();
        Intent intent = getIntent();
        if (intent.getBooleanExtra(NEW_BOOK, false)) {
            actionBar.setTitle("Add Book");
            Book book = intent.getParcelableExtra("BOOK");
            Fragment fragment = book == null ? new AddBookFragment() : AddBookFragment.newInstance(book);
            hostFragment(fragment);
        } else {
            Book book = intent.getParcelableExtra(BOOK_KEY);
            hostFragment(EditFragment.newInstance(book));
            actionBar.setTitle(getResources().getString(R.string.title_edit));
        }
    }

    public void hostFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_container, fragment).commit();
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM |
                ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public static Intent createEditIntent(Context context, int id, Book book) {
        return new Intent(context, EditActivity.class).putExtra(BOOK_KEY, book)
                .putExtra(NEW_BOOK, false);
    }


    public static Intent fillOutNewBook(Context context, @Nullable Book book) {
        Intent intent = new Intent(context, EditActivity.class).putExtra(NEW_BOOK, true);
        intent.putExtra("BOOK", book);
        return intent;
    }


}
