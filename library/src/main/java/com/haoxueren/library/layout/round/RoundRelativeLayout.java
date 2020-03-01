package com.haoxueren.library.layout.round;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * create by haomingliang on 2020/2/23
 */
public class RoundRelativeLayout extends RelativeLayout {
    public RoundRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        RoundCornerHelper.setRoundBackground(this, attrs);
    }
}
