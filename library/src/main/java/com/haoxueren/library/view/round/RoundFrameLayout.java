package com.haoxueren.library.view.round;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 圆角矩形 FrameLayout
 * 1. 颜色背景支持自定义四个角的弧度
 * 2. 图片只支持radius属性，不支持对四个角分别定义
 * <p>
 * create by haomingliang on 2020/2/22
 */
public class RoundFrameLayout extends FrameLayout {

    public RoundFrameLayout(Context context) {
        this(context, null);
    }

    public RoundFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        RoundCornerHelper.setRoundBackground(this, attrs);
    }


}
