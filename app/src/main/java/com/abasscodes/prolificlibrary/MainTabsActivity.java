package com.abasscodes.prolificlibrary;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;
import com.abasscodes.prolificlibrary.ui.ExplorerFragment;
import com.abasscodes.prolificlibrary.ui.show_all_books.AllBooksFragment;
import com.abasscodes.prolificlibrary.ui.show_all_books.TabPresenter;
import com.abasscodes.prolificlibrary.view.TabAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainTabsActivity extends BasePresenterActivity<TabPresenter> implements AllBooksFragment.FragmentCommunication {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    private String TAG = MainTabsActivity.class.getSimpleName();
    public TabAdapter adapter;


    @Override
    public TabPresenter getPresenter() {
        return TabPresenter.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_coord_layout);
        initializeViews();
        if (viewPager != null) {
            initViewPager(viewPager);
        }
        tabs.setupWithViewPager(viewPager);
    }

    public void initViewPager(ViewPager viewPager) {
        adapter = new TabAdapter(this);
        adapter.addFragment(new AllBooksFragment(), "Library");
        adapter.addFragment(new ExplorerFragment(), "Explore");
        viewPager.setAdapter(adapter);
    }


    public void initializeViews() {
        ButterKnife.bind(this);
        setupActionBar();

    }


    public void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE |
                ActionBar.DISPLAY_SHOW_HOME);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.mipmap.favicon);
    }


    @Override
    public void setCheckedOutBooks(ArrayList<Book> books) {
        adapter.setCheckedOutBooks(books);
        adapter.notifyDataSetChanged();
    }
}