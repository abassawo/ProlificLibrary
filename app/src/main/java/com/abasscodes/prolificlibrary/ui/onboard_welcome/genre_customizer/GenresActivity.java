package com.abasscodes.prolificlibrary.ui.onboard_welcome.genre_customizer;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.abasscodes.prolificlibrary.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/21/16.
 */

public class GenresActivity extends AppCompatActivity{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.genre_recycler_view)RecyclerView rv;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.genre_chooser_activity);
        ButterKnife.bind(this);
        initRv();
        setupActionBar(toolbar, false);

    }

    public void initRv() {
            String[] genres = getResources().getStringArray(R.array.genres);
            List<String> genreList = Arrays.asList(genres);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new GenreAdapter(this, genreList));

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

//    public void hostFragment(Fragment fragment){
//        getSupportFragmentManager().beginTransaction().replace(R.id.genre_main_container, fragment).commit();
//    }
}
