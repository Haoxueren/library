package com.haoxueren.library.view.round;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/**
 * create by haomingliang on 2020/2/23
 */
public class RoundCornerHelper {

    public static Drawable getRoundDrawable(Drawable background, RoundAttributeHolder holder) {
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
        // 为图片背景添加圆角
        if (background instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) background).getBitmap();
            Bitmap roundBitmap = RoundCornerHelper.getRoundBitmap(bitmap, holder.radius);
            return new BitmapDrawable(holder.context.getResources(), roundBitmap);
        }
        return background; // 均未处理，原路返回
    }

    /**
     * 为GradientDrawable设置Radius
     */
    public static void setGradientDrawableRadius(GradientDrawable drawable, RoundAttributeHolder holder) {
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

    /**
     * 绘制圆角矩形图片
     */
    public static Bitmap getRoundBitmap(Bitmap bitmap, int radius) {
        // 创建一个新的Bitmap
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        // 绘制一个圆角矩形
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        // 绘制圆角矩形与图片的交集
        PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN); // 取交集
        paint.setXfermode(mode);
        canvas.drawBitmap(bitmap, new Matrix(), paint); // 注：不用Matrix图片缩放有问题
        return newBitmap;
    }
}
