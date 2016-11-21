package com.abasscodes.prolificlibrary.interactions.onboard_welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.TextUtilHelper;
import com.abasscodes.prolificlibrary.interactions.show_all_books.MainTabsActivity;
import com.redbooth.WelcomeCoordinatorLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by C4Q on 11/21/16.
 */

public class WelcomeActivity extends AppCompatActivity {


    WelcomeCoordinatorLayout coordinatorLayout;
    @Bind(R.id.next)
    TextView nextButton;
    @Bind(R.id.android_reading_icon)
    @Nullable ImageView droidImg;
    @Bind(R.id.submit_button)
    Button submitButton;


    private AutoCompleteTextView nameField, emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);
        coordinatorLayout = (WelcomeCoordinatorLayout) findViewById(R.id.welcome_coordinator);
        coordinatorLayout.addPage(R.layout.welcome_screen_one,
                R.layout.welcome_screen_two);
        ButterKnife.bind(this);
//
//        submitButton = (Button) findViewById(R.id.submit_button);
    }


    @OnClick(R.id.next)
    void next() {
        coordinatorLayout.setCurrentPage(coordinatorLayout.getNumOfPages() - 1, true);
        nextButton.setVisibility(View.GONE);
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameField = (AutoCompleteTextView) findViewById(R.id.user_name);
                emailField = (AutoCompleteTextView) findViewById(R.id.email);
                submit();
            }
        });
    }



    void submit() {
        if (readyToAdvance()) {
            PreferenceWrapper.setUserName(this, nameField.getText().toString());
            PreferenceWrapper.setEmail(this, emailField.getText().toString());
            PreferenceWrapper.disableWelcome(this);
            Intent intent = new Intent(this, MainTabsActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please ensure both fields are filled out", Toast.LENGTH_LONG).show();
        }

    }

    public boolean readyToAdvance() {
        return TextUtilHelper.hasText(nameField) && TextUtilHelper.hasText(emailField);
    }

}
