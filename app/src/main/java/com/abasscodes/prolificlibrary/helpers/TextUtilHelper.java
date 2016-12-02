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

    public static boolean hasValidIntText(EditText editText) {
        if(!hasText(editText)) return false;
        char[] numChars = editText.getText().toString().toCharArray();
        boolean areDigits = true;
        for(char c : numChars){
            if(!Character.isDigit(c)) areDigits = false;
        }
        return areDigits;
    }

    public static boolean isValidNote(EditText pageField, EditText noteField) {
        return hasValidIntText(pageField) && hasText(noteField);
    }
}
