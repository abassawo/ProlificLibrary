package com.abasscodes.prolificlibrary.ui.onboard_welcome;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.ui.onboard_welcome.genre_customizer.GenrePickerSlide;
import com.abasscodes.prolificlibrary.MainTabsActivity;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragment;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

/**
 * Created by C4Q on 11/21/16.
 */

public class WelcomeActivity extends MaterialIntroActivity implements FirstOnboardSlide.Callback {
    SlideFragmentBuilder builder = new SlideFragmentBuilder();
    private MessageButtonBehaviour buttonBehavior;
    private IViewTranslation translation;
    private String userName, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonBehavior = new MessageButtonBehaviour(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage(getResources().getString(R.string.prolific_description));
            }
        }, getResources().getString(R.string.app_name));
        initSlides();

    }

    public void initSlides() {
        addSlide(new FirstOnboardSlide(), buttonBehavior);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addPermissionSlide();
        }
        addSlide(new GenrePickerSlide(), buttonBehavior);
        translation = new IViewTranslation() {
            @Override
            public void translate(View view, @FloatRange(from = 0.0, to = 1.0) float percentage) {
                PreferenceHelper.disableWelcome(WelcomeActivity.this);
                startActivity(new Intent(WelcomeActivity.this, MainTabsActivity.class));
                finish();
            }
        };
        getNextButtonTranslationWrapper().setExitTranslation(translation);
        enableLastSlideAlphaExitTransition(true);
        addLastSlide();
    }


    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addPermissionSlide() {
        SlideFragment permissionSlide = builder
                .backgroundColor(R.color.colorPrimary)
                .buttonsColor(R.color.colorAccent)
                .neededPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                .title("Grant Permissions")
                .description("Get Started")
                .build();
        boolean hasPermissions = permissionSlide.hasNeededPermissionsToGrant();
        permissionSlide.setAllowEnterTransitionOverlap(hasPermissions);
        addSlide(permissionSlide, buttonBehavior);
    }

    public void addOnboardSlide() {
        addSlide(builder.backgroundColor(R.color.colorPrimary)
                        .buttonsColor(R.color.colorAccent)
                        .title("Looks like it's your first time")
                        .description(getResources().getString(R.string.join_prolific))
                        .build(),
                buttonBehavior);
    }

    public void addLastSlide() {
        addSlide(builder.backgroundColor(R.color.colorPrimary)
                .buttonsColor(R.color.colorAccent)
                .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                .title("You're all set.")
                .description("Start using Prolific Library")
                .build(), buttonBehavior);
    }

    @Override
    public void setUsername(String username) {
        this.userName = username;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
