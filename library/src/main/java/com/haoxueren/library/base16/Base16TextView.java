package com.haoxueren.library.base16;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * 支持解码显示Base64编码的TextView
 */
public class Base16TextView extends AppCompatTextView implements TextWatcher {

    public Base16TextView(Context context) {
        this(context, null);
    }

    public Base16TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.addTextChangedListener(this);
    }

    /**
     * Same as #setText()
     */
    public void setBase16(String base16) {
        this.setText(Base16.decode(base16));
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();
        if (Base16.isBase16(text)) {
            this.setText(Base16.decode(text));
        }
    }
}
