package com.abasscodes.prolificlibrary.presenter;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.data.BookRepository;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.view.TabAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AbstractPresenterActivity {

    private static final String BOOKS_SAV = "books_key";
    private TabAdapter adapter;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    private String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        if(savedInstanceState != null){
            ArrayList<Book> books = savedInstanceState.getParcelableArrayList(BOOKS_SAV);
            onAllBooksLoaded(books);
        }else{
            requestData();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }
    

    @Override
    public void onConnectionFailure() {
        onAllBooksLoaded(new ArrayList<Book>());
        View view = findViewById(R.id.main_content);
        Snackbar.make(view, "Internet is not on ", Snackbar.LENGTH_INDEFINITE).setAction("Connect", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNetworkSettings();
            }
        }).show();
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void initializeViews(){
        ButterKnife.bind(this);
        mDrawerLayout.setForegroundGravity(Gravity.LEFT);
        setupNavBar(navView);
        setupActionBar();
    }

    @Override
    public void onAllBooksLoaded(ArrayList<Book> books) {
        if(books == null) {
            showNetworkSettings();
            return;
        }
        adapter = new TabAdapter(this, books);
        if (viewPager != null) {
            setupViewPager();
        }
    }


    public void requestData(){
        if(ConnectionUtil.isConnected()) {
            new BookRepository().fetchBooks();
        } else{
            onConnectionFailure();
        }
    }


    public void setupViewPager(){
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }


    public void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE |
                ActionBar.DISPLAY_SHOW_HOME);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    public void setupNavBar(NavigationView nav) {
        nav.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(mDrawerLayout == null) return false;
                else if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawers();
                } else
//                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;
            case R.id.menu_item_add:
                fillOutNewBookForm();
                break;
            case R.id.menu_item_deleteAll:
                break;
        }
        return true;
    }



}