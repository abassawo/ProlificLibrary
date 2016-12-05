package com.abasscodes.prolificlibrary.helpers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.abasscodes.prolificlibrary.MainTabsActivity;
import com.abasscodes.prolificlibrary.user_interactions.onboard_welcome.WelcomeActivity;

/**
 * Created by C4Q on 11/11/16.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        if (PreferenceHelper.isFirstRun(this)) {
            intent = new Intent(this, WelcomeActivity.class);
        }else{
            intent = new Intent(this, MainTabsActivity.class);
        }
        startActivity(intent);
        finish();


    }

}