package com.haoxueren.library.layout.round;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * create by haomingliang on 2020/2/23
 */
public class RoundLinearLayout extends LinearLayout {
    public RoundLinearLayout(Context context) {
        this(context, null);
    }

    public RoundLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        RoundCornerHelper.setRoundBackground(this, attrs);
    }


}
