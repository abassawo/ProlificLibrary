package com.abasscodes.prolificlibrary.ui.editor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.ui.detail.DetailFragment;

/**
 * Created by C4Q on 11/11/16.
 */
public class EditActivity extends AppCompatActivity {
    private int bookId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setTitle(getResources().getString(R.string.title_edit));
        bookId = getIntent().getIntExtra(DetailFragment.BOOK_ARG_ID, 0);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_container, getFragment(bookId)).commit();
    }

    public Fragment getFragment(Integer id) {
        if(id == null) {
            return EditFragment.newInstance(Integer.MIN_VALUE, "", "", "", "");
            //Using negative ID number to denote books that have not yet been created.
        } else{
            String title = getIntent().getStringExtra(DetailFragment.BOOK_TITLE);
            String author = getIntent().getStringExtra(DetailFragment.BOOK_AUTHOR);
            String pubs = getIntent().getStringExtra(DetailFragment.BOOK_PUBS);
            String tags= getIntent().getStringExtra(DetailFragment.BOOK_TAGS);
            return EditFragment.newInstance(id, title, author, pubs, tags);
        }

    }


}
