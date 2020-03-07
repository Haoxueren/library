package com.haoxueren.library.layout.round;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * create by haomingliang on 2020/2/23
 */
public class RoundCornerHelper {

    public static void setRoundBackground(View target, AttributeSet attrs) {
        RoundAttributeHolder holder = new RoundAttributeHolder(target.getContext(), attrs);
        Drawable background = target.getBackground();
        Drawable roundDrawable = RoundCornerHelper.getRoundDrawable(background, holder);
        target.setBackground(roundDrawable);
    }

    /**
     * 支持纯色背景和Shape背景
     * 如果背景为图片，请使用RoundImageLayout
     */
    private static Drawable getRoundDrawable(Drawable background, RoundAttributeHolder holder) {
        // 为纯色背景添加圆角
        if (background instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) background;
            int color = colorDrawable.getColor();
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(color);
            RoundCornerHelper.setGradientDrawableRadius(gradientDrawable, holder);
            return gradientDrawable;
        }
        // 为Shape背景添加圆角
        if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            RoundCornerHelper.setGradientDrawableRadius(gradientDrawable, holder);
            return gradientDrawable;
        }
        return background; // 均未处理，原路返回
    }

    /**
     * 为GradientDrawable设置Radius
     */
    private static void setGradientDrawableRadius(GradientDrawable drawable, RoundAttributeHolder holder) {
        if (holder.radius > 0) {
            drawable.setCornerRadius(holder.radius);
        } else {
            drawable.setCornerRadii(new float[]{
                    holder.radiusTopLeft, holder.radiusTopLeft,
                    holder.radiusTopRight, holder.radiusTopRight,
                    holder.radiusBottomRight, holder.radiusBottomRight,
                    holder.radiusBottomLeft, holder.radiusBottomLeft});
        }
    }
}
