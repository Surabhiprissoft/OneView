package com.sbi.oneview.utils;

import android.text.InputFilter;
import android.text.Spanned;

import com.sbi.oneview.R;
import com.sbi.oneview.base.App;

public class CustomInputFilter implements InputFilter {
    private final String allowedCharacters = App.getAppContext().getString(R.string.allow_characters);

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        StringBuilder filtered = new StringBuilder();
        for (int i = start; i < end; i++) {
            char character = source.charAt(i);
            if (allowedCharacters.contains(App.getAppContext().getString(character))) {
                filtered.append(character);
            }
        }
        return filtered.toString();
    }

}
