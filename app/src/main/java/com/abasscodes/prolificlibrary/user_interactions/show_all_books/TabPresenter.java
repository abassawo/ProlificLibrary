package com.abasscodes.prolificlibrary.user_interactions.show_all_books;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.abasscodes.prolificlibrary.MainTabsActivity;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.presenter.AbstractPresenter;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

/**
 * Created by C4Q on 11/15/16.
 */

public class TabPresenter extends AbstractPresenter implements Presenter {

    private static final String TAG = TabPresenter.class.getSimpleName();
    private static TabPresenter instance;
    private static MainTabsActivity presenterActivity;


    public TabPresenter(BasePresenterActivity activity) {
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
    public void onConnectionFailure() {
        View view = presenterActivity.findViewById(R.id.main_content);
        Snackbar.make(view, "Internet is not on ", Snackbar.LENGTH_INDEFINITE).setAction("Connect", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNetworkSettings();
            }
        }).show();
    }


}
