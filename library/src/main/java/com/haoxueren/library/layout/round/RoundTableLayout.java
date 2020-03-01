package com.haoxueren.library.layout.round;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;

/**
 * create by haomingliang on 2020/2/23
 */
public class RoundTableLayout extends TableLayout {

    public RoundTableLayout(Context context) {
        this(context, null);
    }

    public RoundTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        RoundCornerHelper.setRoundBackground(this, attrs);
    }
}
