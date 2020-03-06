package com.haoxueren.library.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 新手引导布局
 * create by haomingliang on 2020/3/6
 */
public class NoviceLayout extends FrameLayout {

    private View highLightView;
    private Rect highLightRect;
    private Paint paint;

    public NoviceLayout(Activity context) {
        this(context, null);
    }

    public NoviceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(0xb3006660);
        highLightRect = new Rect();
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        View parent = (View) this.getParent();
        highLightView.getGlobalVisibleRect(highLightRect);
        highLightView.setTranslationY(parent.getTop());
        canvas.drawRect(highLightRect, paint);
    }

    public static NoviceLayout with(Activity activity) {
        FrameLayout rootLayout = activity.findViewById(android.R.id.content);
        NoviceLayout noviceLayout = new NoviceLayout(activity);
        rootLayout.addView(noviceLayout);
        return noviceLayout;
    }

    public void highLight(View view) {
        this.highLightView = view;
        this.invalidate();
    }

    public void dismiss() {
        ViewGroup parent = (ViewGroup) this.getParent();
        parent.removeView(this);
    }

}
