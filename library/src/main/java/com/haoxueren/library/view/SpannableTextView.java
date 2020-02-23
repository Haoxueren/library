package com.haoxueren.library.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;

/**
 * 一款支持Span的TextView
 */
public class SpannableTextView extends AppCompatTextView {

    public SpannableTextView(Context context) {
        super(context);
    }

    public SpannableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置字体颜色
     */
    public void setTextColor(int color, int start, int end) {
        CharSequence text = this.getText();
        SpannableString spannableText = new SpannableString(text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        spannableText.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        this.setText(spannableText);
    }

    /**
     * 设置字体大小，单位为dp
     */
    public void setTextSize(int textSizeDip, int start, int end) {
        CharSequence text = this.getText();
        SpannableString spannableText = new SpannableString(text);
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(textSizeDip, true);
        spannableText.setSpan(sizeSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        this.setText(spannableText);
    }

    /**
     * 设置下划线
     */
    public void setUnderline(int start, int end) {
        CharSequence text = this.getText();
        SpannableString spannableText = new SpannableString(text);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableText.setSpan(underlineSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        this.setText(spannableText);
    }

    public void setUrlSpan(String url, int start, int end) {
        CharSequence text = this.getText();
        SpannableString spannableText = new SpannableString(text);
        URLSpan urlSpan = new URLSpan(url);
        spannableText.setSpan(urlSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        this.setText(spannableText);
    }

    public void setImageSpan(@DrawableRes int resId, int position) {
        this.setImageSpan(resId, position, position + 1);
    }

    public void setImageSpan(@DrawableRes int resId, int start, int end) {
        CharSequence text = this.getText();
        SpannableString spannableText = new SpannableString(text);
        ImageSpan imageSpan = new ImageSpan(getContext(), resId);
        spannableText.setSpan(imageSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        this.setText(spannableText);
    }
}