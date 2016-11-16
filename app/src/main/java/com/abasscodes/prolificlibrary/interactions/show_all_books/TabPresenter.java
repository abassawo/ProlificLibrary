package com.abasscodes.prolificlibrary.interactions.show_all_books;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.presenter.AbstractPresenter;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;
import com.abasscodes.prolificlibrary.view.BookAdapter;
import com.abasscodes.prolificlibrary.view.TabAdapter;
import com.abasscodes.prolificlibrary.view.tab_fragments.AllBooksFragment;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/15/16.
 */

public class TabPresenter extends AbstractPresenter implements Presenter {

    private static final String TAG = TabPresenter.class.getSimpleName();
    private static TabPresenter instance;
    private static MainTabsActivity presenterActivity;
    private TabLayout tabs;
    ViewPager viewPager;
    private TabAdapter adapter;

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
    public void onAllBooksLoaded(Activity activity, ArrayList<Book> books) {
        if (books == null)  {
            Log.d(TAG, "Empty response");
            return;
        }
        adapter  = new TabAdapter(books, (AppCompatActivity) activity);
        viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
        tabs = (TabLayout) activity.findViewById(R.id.tabs);
        if (viewPager != null) {
            viewPager.setAdapter(adapter);
            tabs.setupWithViewPager(viewPager);
        }

//        adapter.onPageSelected(0);
    }

    @Override
    public void onAllBooksLoaded(View view, ArrayList<Book> books) {
//        if (books == null)  {
//            Log.d(TAG, "Empty response");
//            return;
//        }
//        adapter  = new TabAdapter(books, (AppCompatActivity) activity);
//        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
//        tabs = (TabLayout) view.findViewById(R.id.tabs);
//        if (viewPager != null) {
//            viewPager.setAdapter(adapter);
//            tabs.setupWithViewPager(viewPager);
//        }

//        adapter.onPageSelected(0);
    }

    public void onAllBooksLoaded(ArrayList<Book> books){
        if(adapter == null) {
            adapter = new TabAdapter(books, activity);
        }
        onAllBooksLoaded(activity, books);
    }


    @Override
    public void updateUI(){
        if(ConnectionUtil.isConnected()) {
            new BookRepository().fetchBooks();
        } else{
            onConnectionFailure();
        }
    }

    public void updateFragmentUI(AllBooksFragment fragment){
        BookAdapter adapter = fragment.rvAdapter;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onConnectionFailure() {
        onAllBooksLoaded(adapter.getItem(0).getView(), new ArrayList<Book>());
        View view = presenterActivity.findViewById(R.id.main_content);
        Snackbar.make(view, "Internet is not on ", Snackbar.LENGTH_INDEFINITE).setAction("Connect", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNetworkSettings();
            }
        }).show();
    }





}
