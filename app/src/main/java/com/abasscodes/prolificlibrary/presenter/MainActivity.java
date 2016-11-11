package com.abasscodes.prolificlibrary.presenter;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments.AllBooksFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AbstractPresenterActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    private String TAG = MainActivity.class.getSimpleName();
    private TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        showAllBooks();
    }

    @Override
    void hostFragmentInTab(TabAdapter.TabType type) {
        int position = adapter.indexOf(type);
        adapter.onPageSelected(position);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void initializeViews(){
        ButterKnife.bind(this);
        mDrawerLayout.setForegroundGravity(Gravity.LEFT);
        setupNavBar(navView);
        setupActionBar();
        if (viewPager != null) {
            setupViewPager(viewPager);
            tabs.setupWithViewPager(viewPager);

        }


    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new TabAdapter(this, viewPager);
    }



    @Override
    protected void onResume() {
        super.onResume();
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



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
//        final MenuItem menuitem = menu.findItem(R.id.nav_switch);
////        SwitchCompat toggle = (SwitchCompat) MenuItemCompat.getActionView(menuitem);
////        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//////                setupAdapter(books, checkedOutOnly);
//////                presenter.showBooksOnlyCheckedOut();
////            }
////        });
//
//
//        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
////                String temp = "";
////                if(query.length() > temp.length()) {
////                    final List<Book> filteredBooks = filter(books, query);
////                    rvAdapter.setBooks(filteredBooks);
////                    rvAdapter.notifyDataSetChanged();
////                    mRecyclerView.scrollToPosition(0);
////                    temp = query;
////                } else if(query.length()<= 0){
////                    rvAdapter.setBooks(books);
////                    rvAdapter.notifyDataSetChanged();
////                    mRecyclerView.scrollToPosition(0);
////                }
//                return false;
//            }
//        });
//        return true;
//    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        } else {
            if((currentFragment instanceof AllBooksFragment)){
                showAllBooks();
            }
            else super.onBackPressed();
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