package com.haoxueren.library.base64;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class Base16EditText extends AppCompatEditText {

    public Base16EditText(Context context) {
        this(context, null);
    }

    public Base16EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public String getBase16() {
        String text = this.getText().toString();
        return Base16.encode(text);
    }

    public void setBase16(String text) {
        if (Base16.isBase16(text)) {
            text = Base16.decode(text);
        }
        this.setText(text);
    }
}
