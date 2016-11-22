package com.abasscodes.prolificlibrary.ui.onboard_welcome;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.TextUtilHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/22/16.
 */

public class FirstOnboardSlide extends BaseSlideFragment {
    @Bind(R.id.user_name)
    EditText nameField;
    @Bind(R.id.email)
    EditText emailField;
    @Bind(R.id.welcome_image_view)
    ImageView imageView;
    private Callback listener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (Callback) activity;
        }catch (ClassCastException cce){
            throw new ClassCastException("Must impement Callback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.welcome_screen_two, container, false);
        ButterKnife.bind(this, view);
        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listener.setUsername(s.toString());
            }
        });
        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

    public interface Callback{

        void setUsername(String username);
        void setEmail(String email);
    }


}
