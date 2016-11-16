package com.abasscodes.prolificlibrary.interactions.show_all_books;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.presenter.AbstractPresenter;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;
import com.abasscodes.prolificlibrary.view.TabAdapter;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/15/16.
 */

public class TabPresenter extends AbstractPresenter implements MainPresenter {

    private static final String TAG = TabPresenter.class.getSimpleName();
    private static TabPresenter instance;
    private static MainTabsActivity presenterActivity;

    public TabPresenter(BasePresenterActivity<AbstractPresenter> activity) {
        super(activity);
    }


    public static TabPresenter getInstance(BasePresenterActivity activity) {
        presenterActivity = (MainTabsActivity) activity;
         if(instance == null){
            instance = new TabPresenter(activity);
        }
        return instance;
    }

    @Override
    public void onAllBooksLoaded(ArrayList<Book> books) {
        if (books == null)  {
            Log.d(TAG, "Empty response");
            return;
        }
        TabAdapter adapter  = new TabAdapter(books);
        ViewPager viewPager = (ViewPager) presenterActivity.findViewById(R.id.viewpager);
        TabLayout tabs = (TabLayout) presenterActivity.findViewById(R.id.tabs);
        if (viewPager != null) {
            viewPager.setAdapter(adapter);
            tabs.setupWithViewPager(viewPager);
        }
    }


    @Override
    public void updateUI(){
        if(ConnectionUtil.isConnected()) {
            new BookRepository().fetchBooks();
        } else{
            onConnectionFailure();
        }
    }

    @Override
    public void onConnectionFailure() {
        onAllBooksLoaded(new ArrayList<Book>());
        View view = presenterActivity.findViewById(R.id.main_content);
        Snackbar.make(view, "Internet is not on ", Snackbar.LENGTH_INDEFINITE).setAction("Connect", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNetworkSettings();
            }
        }).show();
    }





}
