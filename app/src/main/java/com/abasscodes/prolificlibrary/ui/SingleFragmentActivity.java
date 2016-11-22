package com.abasscodes.prolificlibrary.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.abasscodes.prolificlibrary.Mvp;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

/**
 * Created by C4Q on 11/21/16.
 */

public class SingleFragmentActivity extends BasePresenterActivity {

    @Override
    public Mvp.Presenter getPresenter() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.single_fragment_activity);
    }

    public void setupActionBar(Toolbar toolbar, boolean showHomeAsUp) {
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE |
                ActionBar.DISPLAY_SHOW_HOME);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(showHomeAsUp);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
    }


    public void setupActionBar(Toolbar toolbar) {
        setupActionBar(toolbar, true);
    }

    public void hostFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.single_fragment_container, fragment).commit();
    }
}
