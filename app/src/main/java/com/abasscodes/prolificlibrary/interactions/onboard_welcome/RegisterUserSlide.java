package com.abasscodes.prolificlibrary.interactions.onboard_welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.TextUtilHelper;

import agency.tango.materialintroscreen.SlideFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/22/16.
 */

public class RegisterUserSlide extends BaseSlideFragment {
    @Bind(R.id.user_name)
    EditText nameField;
    @Bind(R.id.email)
    EditText emailField;
    @Bind(R.id.welcome_image_view)
    ImageView imageView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.welcome_screen_two, container, false);
        ButterKnife.bind(this, view);
        imageView.setImageResource(agency.tango.materialintroscreen.R.drawable.ic_next);
        return view;
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        return getResources().getString(R.string.check_input_prompt);
    }


    @Override
    public boolean canMoveFurther() {
        return TextUtilHelper.hasText(nameField) && TextUtilHelper.hasText(emailField);
    }


}
