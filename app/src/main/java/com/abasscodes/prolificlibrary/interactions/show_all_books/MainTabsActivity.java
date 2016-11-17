package com.abasscodes.prolificlibrary.interactions.show_all_books;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.interactions.edit_book.AddBookFragment;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;
import com.abasscodes.prolificlibrary.view.TabAdapter;
import com.abasscodes.prolificlibrary.view.tab_fragments.AllBooksFragment;
import com.abasscodes.prolificlibrary.view.tab_fragments.CheckedOutBooksFragment;
import com.abasscodes.prolificlibrary.view.tab_fragments.CompletedBooksFragment;
import com.abasscodes.prolificlibrary.view.tab_fragments.ExplorerFragment;
import com.abasscodes.prolificlibrary.view.tab_fragments.ReadLaterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainTabsActivity extends BasePresenterActivity<TabPresenter> implements AllBooksFragment.FragmentCommunication {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navView;
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
        setContentView(R.layout.activity_main);
        initializeViews();
        if (viewPager != null) {
            initViewPager(viewPager);
        }
        tabs.setupWithViewPager(viewPager);
    }

    public void initViewPager(ViewPager viewPager) {
        adapter = new TabAdapter(this);
        adapter.addFragment(new AllBooksFragment(), "Library");
        adapter.addFragment(ExplorerFragment.getInstance(), "Explore");
        adapter.addFragment(AvailableOfflineFragment.getInstance(), "Offline");
        viewPager.setAdapter(adapter);

    }


    @TargetApi(Build.VERSION_CODES.M)
    public void initializeViews() {
        ButterKnife.bind(this);
        mDrawerLayout.setForegroundGravity(Gravity.LEFT);
        setupNavBar(navView);
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
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout == null) return false;
                else if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawers();
                } else
//                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;
            case R.id.menu_item_add:
                getPresenter().fillOutNewBookForm();
                break;
            case R.id.menu_item_deleteAll:
                break;
        }
        return true;
    }


    @Override
    public void setCheckedOutBooks(ArrayList<Book> books) {
//        adapter.setCheckedOutBooks(books);
        adapter.notifyDataSetChanged();
    }
}