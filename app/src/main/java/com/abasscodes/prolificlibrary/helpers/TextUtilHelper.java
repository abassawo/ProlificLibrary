package com.abasscodes.prolificlibrary.helpers;

import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by C4Q on 11/21/16.
 */

public class TextUtilHelper {

    public static boolean hasText(EditText editText){
        return !TextUtils.isEmpty(editText.getText());
    }

    public static boolean isEmpty(EditText editText) {
        return !hasText(editText);
    }
}
