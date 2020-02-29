package com.haoxueren.library.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.haoxueren.library.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 专门用于显示日期的TextView
 * create by haomingliang on 2020/2/29
 */
public class DateTextView extends AppCompatTextView {

    private SimpleDateFormat format;

    public DateTextView(Context context) {
        this(context, null);
    }

    public DateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DateTextView);
        String pattern = typedArray.getString(R.styleable.DateTextView_dateFormat);
        pattern = TextUtils.isEmpty(pattern) ? "yyyy-MM-dd HH:mm" : pattern;
        format = new SimpleDateFormat(pattern, Locale.CHINA);
        typedArray.recycle();
    }

    public void setDate(Date date) {
        String dateText = format.format(date);
        this.setText(dateText);
    }

    public void setMillis(long millis) {
        String dateText = this.format.format(new Date(millis));
        this.setText(dateText);
    }


}
