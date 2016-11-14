package com.abasscodes.prolificlibrary.ui.editor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.ui.detail.DetailFragment;

/**
 * Created by C4Q on 11/11/16.
 */
public class EditActivity extends AppCompatActivity {

    private static final String BOOK_KEY = "book_key";
    private int bookId;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.title_edit));
        FragmentManager fm = getSupportFragmentManager();
        Book book = getIntent().getParcelableExtra(BOOK_KEY);
        fm.beginTransaction().replace(R.id.main_container, getFragment(book)).commit();
    }

    public static Intent maKeEditIntent(Context context, Book book){
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(BOOK_KEY, book);
        return intent;
    }

    public Fragment getFragment(Book book) {
        if(book == null) {
            return NewBookFragment.newInstance();
            //Using negative ID number to denote books that have not yet been created.
        } else{
            return EditFragment.newInstance(book);
        }

    }


}
