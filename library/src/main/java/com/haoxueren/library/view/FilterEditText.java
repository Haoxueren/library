package com.haoxueren.library.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.haoxueren.library.log.MyLog;

/**
 * 用户新输入的内容必须满足指定的正则表达式
 * create by haomingliang on 2020/3/5
 */
public class FilterEditText extends AppCompatEditText implements InputFilter {

    private String regex = "[0-9-:\\s]*";

    public FilterEditText(Context context) {
        super(context);
    }

    public FilterEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setFilters(new InputFilter[]{this});
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int destStart, int destEnd) {
        String input = source.toString();
        if (input.matches(regex)) {
            return null;
        }
        return "";
    }
}
