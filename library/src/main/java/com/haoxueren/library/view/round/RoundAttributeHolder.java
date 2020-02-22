package com.haoxueren.library.view.round;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.haoxueren.library.R;

/**
 * 自定义圆角属性的统一管理对象
 */
public class RoundAttributeHolder {

    int radius, radiusTopLeft, radiusTopRight, radiusBottomLeft, radiusBottomRight;

    public RoundAttributeHolder(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RoundFrameLayout);
        radius = attributes.getDimensionPixelSize(R.styleable.RoundFrameLayout_radius, 0);
        radiusTopLeft = attributes.getDimensionPixelSize(R.styleable.RoundFrameLayout_radiusTopLeft, 0);
        radiusTopRight = attributes.getDimensionPixelSize(R.styleable.RoundFrameLayout_radiusTopRight, 0);
        radiusBottomLeft = attributes.getDimensionPixelSize(R.styleable.RoundFrameLayout_radiusBottomLeft, 0);
        radiusBottomRight = attributes.getDimensionPixelSize(R.styleable.RoundFrameLayout_radiusBottomRight, 0);
        attributes.recycle();
    }
}