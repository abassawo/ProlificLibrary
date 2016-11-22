package com.abasscodes.prolificlibrary.interactions.onboard_welcome;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageButton;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.interactions.show_all_books.MainTabsActivity;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragment;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

/**
 * Created by C4Q on 11/21/16.
 */

public class WelcomeActivity extends MaterialIntroActivity implements GenreChooserFragment.Callback{
    SlideFragmentBuilder builder = new SlideFragmentBuilder();
    private boolean allowMainContentNext = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);
        getNextButtonTranslationWrapper().setExitTranslation(new IViewTranslation() {
            @Override
            public void translate(View view, @FloatRange(from = 0.0, to = 1.0) float percentage) {
                PreferenceWrapper.disableWelcome(WelcomeActivity.this);
                startActivity(new Intent(WelcomeActivity.this, MainTabsActivity.class));
                finish();
            }
        });
        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });
        getSkipButtonTranslationWrapper().setEnterTranslation(new IViewTranslation() {
            @Override
            public void translate(View view, @FloatRange(from = 0.0, to = 1.0) float percentage) {
                view.setAlpha(percentage);
            }
        });
        addOnboardSlide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addPermissionSlide();
        }
        addSlide(new RegisterUserSlide());
        GenreChooserFragment genrePicker = new GenreChooserFragment();
        addSlide(genrePicker);
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
        addSlide(permissionSlide,
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage(getResources().getString(R.string.prolific_description));
                    }
                }, "Prolific Library"));
    }

    public void addOnboardSlide() {
        addSlide(builder.backgroundColor(R.color.colorPrimary)
                        .buttonsColor(R.color.colorAccent)
                        .title("Looks like it's your first time")
                        .description(getResources().getString(R.string.join_prolific))
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage(getResources().getString(R.string.prolific_description));
                    }
                }, "Prolific Library"));
    }

    public void addLastSlide(){
        addSlide(builder.backgroundColor(R.color.colorPrimary)
                        .buttonsColor(R.color.colorAccent)
                        .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                        .title("You're all set.")
                        .description("Start using Prolific Library")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage(getResources().getString(R.string.prolific_description));
                    }
                }, "Prolific Library"));
    }


    @Override
    public void allowMainContentTransition(boolean allow) {
        allowMainContentNext = true;
    }

}
