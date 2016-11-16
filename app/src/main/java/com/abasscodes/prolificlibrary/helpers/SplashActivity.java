package com.abasscodes.prolificlibrary.helpers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abasscodes.prolificlibrary.interactions.show_all_books.MainTabsActivity;

/**
 * Created by C4Q on 11/11/16.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainTabsActivity.class);
        startActivity(intent);
        finish();
    }
}
