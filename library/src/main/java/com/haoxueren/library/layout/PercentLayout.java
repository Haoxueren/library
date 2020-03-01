package com.haoxueren.library.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.haoxueren.library.R;

/**
 * 百分比布局
 * 注：1. 宽高均不支持wrap_content属性
 * 2. 若使用自定义属性时报错，可以使用AppCompatXXX或tools:ignore抑制报错提示
 * create by haomingliang on 2020/3/1
 */
public class PercentLayout extends ViewGroup {

    public PercentLayout(Context context) {
        this(context, null);
    }

    public PercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 测量所有孩子的宽高
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = this.getChildCount();
        int parentWidth = right - left;
        int parentHeight = bottom - top;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue; // GONE掉的child无须布局
            }
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            float percentX = layoutParams.percentX;
            float percentY = layoutParams.percentY;
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            float centerX = left + parentWidth * percentX;
            float centerY = top + parentHeight * percentY;
            int childLeft = (int) (centerX - childWidth / 2);
            int childTop = (int) (centerY - childHeight / 2);
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PercentLayout.LayoutParams(getContext(), attrs);
    }


    public static class LayoutParams extends MarginLayoutParams {

        public float percentX, percentY;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentLayout_Layout);
            percentX = typedArray.getFloat(R.styleable.PercentLayout_Layout_layout_xPercent, 0);
            percentY = typedArray.getFloat(R.styleable.PercentLayout_Layout_layout_yPercent, 0);
            typedArray.recycle();
        }
    }
}
