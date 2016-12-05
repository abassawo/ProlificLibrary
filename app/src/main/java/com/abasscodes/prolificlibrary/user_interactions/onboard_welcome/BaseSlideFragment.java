package com.abasscodes.prolificlibrary.user_interactions.onboard_welcome;

import com.abasscodes.prolificlibrary.R;

import agency.tango.materialintroscreen.SlideFragment;

/**
 * Created by C4Q on 11/22/16.
 */

public class BaseSlideFragment  extends SlideFragment {

    @Override
    public int backgroundColor() {
        return R.color.colorPrimary;
    }


    @Override
    public int buttonsColor() {
        return R.color.colorAccent;
    }

    @Override
    public boolean canMoveFurther() {
        return false;
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        return getString(R.string.error_message);
    }


}