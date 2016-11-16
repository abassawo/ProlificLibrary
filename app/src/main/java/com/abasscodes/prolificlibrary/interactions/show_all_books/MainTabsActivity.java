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
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.interactions.edit_book.AddBookFragment;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;
import com.abasscodes.prolificlibrary.view.TabAdapter;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainTabsActivity extends BasePresenterActivity<TabPresenter> {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    private String TAG = MainTabsActivity.class.getSimpleName();
    private TabAdapter adapter;


    @Override
    public TabPresenter getPresenter() {
        return TabPresenter.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        getPresenter().updateUI();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case DELETED_ITEM_CODE:
//                getPresenter().updateUI();
//                break;
//            case AddBookFragment.ADD_BOOK_CODE:
//                getPresenter().updateUI();
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().updateUI();
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


}