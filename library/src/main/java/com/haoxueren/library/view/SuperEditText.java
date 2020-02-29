package com.haoxueren.library.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * create by haomingliang on 2020/2/28
 */
public class SuperEditText extends AppCompatEditText {

    public SuperEditText(Context context) {
        this(context, null);
    }

    public SuperEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 返回EditText内容是否为空
     */
    public boolean isEmpty() {
        String text = this.getText().toString();
        return TextUtils.isEmpty(text);
    }

    /**
     * 检查EditText内容是否为空，如果为空则抛出异常
     */
    public void checkEmpty(String message) {
        if (this.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检查EditText内容是否匹配给定的正则表达式，如果不匹配则抛出异常
     */
    public void checkRegex(String regex,String message) {
        String text = this.getText().toString();
        if (!text.matches(regex)) {
            throw new IllegalArgumentException(message);
        }
    }
}
