package com.abasscodes.prolificlibrary.ui.show_all_books;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.AbstractPresenter;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;
import com.abasscodes.prolificlibrary.view.TabAdapter;
import com.abasscodes.prolificlibrary.view.tab_fragments.NotesFragment;

import java.util.ArrayList;

/**
 * Created by C4Q on 11/15/16.
 */

public class TabPresenter extends AbstractPresenter implements Presenter {

    private static final String TAG = TabPresenter.class.getSimpleName();
    private static TabPresenter instance;
    private static MainTabsActivity presenterActivity;
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
        try {
            MainTabsActivity tabsActivity = (MainTabsActivity) activity;
            if(tabsActivity != null){
                adapter = tabsActivity.adapter;
                adapter.addFragment(0, NotesFragment.getInstance(), "Library");
                adapter.notifyDataSetChanged();
            }

        }catch (Exception e){

        }

    }


    @Override
    public void onConnectionFailure() {
        View view = presenterActivity.findViewById(R.id.main_content);
        Snackbar.make(view, "Internet is not on ", Snackbar.LENGTH_INDEFINITE).setAction("Connect", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNetworkSettings();
            }
        }).show();
    }

    @Override
    public void updateUI() {
        if(presenterActivity.adapter != null) {
            adapter = presenterActivity.adapter;
            NotesFragment fragment = (NotesFragment) adapter.getItem(0);
//            fixme fragment.refresh();
        }

    }


    private void showReturnDialog(Book book) {

        showCheckOutDialog(book); //fixme
    }
}
