package com.haoxueren.library.layout.round;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.haoxueren.library.R;

/**
 * 圆角图片布局
 * create by haomingliang on 2020/3/7
 */
public class RoundImageLayout extends FrameLayout {

    public RoundImageLayout(Context context) {
        this(context, null);
    }

    public RoundImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageLayout);
        int radius = typedArray.getDimensionPixelSize(R.styleable.RoundImageLayout_radius, 0);
        typedArray.recycle();
        // 设置为圆角背景
        BitmapDrawable background = (BitmapDrawable) this.getBackground();
        Bitmap bitmap = background.getBitmap();
        Bitmap roundBitmap = this.getRoundBitmap(bitmap, radius);
        BitmapDrawable roundBackground = new BitmapDrawable(context.getResources(), roundBitmap);
        this.setBackground(roundBackground);
    }


    /**
     * 绘制圆角矩形图片
     */
    private Bitmap getRoundBitmap(Bitmap bitmap, int radius) {
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
