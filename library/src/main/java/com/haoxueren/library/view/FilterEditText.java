package com.haoxueren.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.haoxueren.library.R;
import com.haoxueren.library.log.MyLog;

import java.util.Arrays;

/**
 * 用户新输入的内容必须满足指定的正则表达式
 * create by haomingliang on 2020/3/5
 */
public class FilterEditText extends AppCompatEditText implements InputFilter {

    private AttributeHolder holder;

    public FilterEditText(Context context) {
        super(context);
    }

    public FilterEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = new AttributeHolder(context, attrs);
        InputFilter[] filters = this.getFilters();
        InputFilter[] newFilters = new InputFilter[filters.length + 1];
        System.arraycopy(filters, 0, newFilters, 0, filters.length);
        newFilters[filters.length] = this; // 追加当前Filter
        this.setFilters(newFilters);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int destStart, int destEnd) {
        if (TextUtils.isEmpty(holder.regex)) {
            return null; // 无限制，不处理
        }
        String input = source.toString();
        if (input.matches(holder.regex)) {
            return null; // 满足条件，放行
        }
        return ""; // 不满足条件，返回空串
    }

    class AttributeHolder {

        String regex;

        AttributeHolder(Context context, AttributeSet attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FilterEditText);
            regex = typedArray.getString(R.styleable.FilterEditText_filterRegex);
            typedArray.recycle();
        }
    }
}
