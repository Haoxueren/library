package com.haoxueren.library.view.round;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 圆角矩形 FrameLayout
 * 1. 颜色背景支持自定义四个角的弧度
 * 2. 图片只支持radius属性，不支持对四个角分别定义
 * create by haomingliang on 2020/2/22
 */
public class RoundFrameLayout extends FrameLayout {

    private RoundAttributeHolder holder;

    public RoundFrameLayout(Context context) {
        this(context, null);
    }

    public RoundFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = new RoundAttributeHolder(context, attrs);
        Drawable background = this.getBackground();
        // 为纯色背景添加圆角
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            int color = colorDrawable.getColor();
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(color);
            RoundCornerHelper.setGradientDrawableRadius(gradientDrawable,holder);
            this.setBackground(gradientDrawable);
            return;
        }
        // 为Shape背景添加圆角
        if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            RoundCornerHelper.setGradientDrawableRadius(gradientDrawable,holder);
            this.setBackground(gradientDrawable);
            return;
        }
        // 为图片背景添加圆角
        if (background instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) background).getBitmap();
            Bitmap roundBitmap = RoundCornerHelper.getRoundBitmap(bitmap, holder.radius);
            this.setBackground(new BitmapDrawable(getResources(), roundBitmap));
            return;
        }
    }
}
