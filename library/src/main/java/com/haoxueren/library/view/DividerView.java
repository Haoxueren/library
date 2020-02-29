package com.haoxueren.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.haoxueren.library.R;

/**
 * 自定义控件：分割线
 * 默认为水平方向，黑色，宽度为1px
 */
public class DividerView extends View {

    private int orientation;

    public DividerView(Context context) {
        this(context, null);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DividerView);
        orientation = attributes.getInt(R.styleable.DividerView_android_orientation, 0);
        attributes.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int wrapContent = ViewGroup.LayoutParams.WRAP_CONTENT;
        int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;

        int width = layoutParams.width;
        int height = layoutParams.height;

        if (orientation == LinearLayout.HORIZONTAL) {
            // 水平方向
            layoutParams.width = (width == wrapContent) ? matchParent : width;
            layoutParams.height = (height == wrapContent) ? 1 : height; // 1px
        } else {
            // 垂直方向
            layoutParams.width = (width == wrapContent) ? 1 : width; // 1px
            layoutParams.height = (height == wrapContent) ? matchParent : height;
        }
        Drawable background = this.getBackground();
        background = (background == null) ? new ColorDrawable(Color.BLACK) : background;
        this.setBackground(background);
        this.requestLayout();
    }
}
