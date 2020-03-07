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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * 新手引导布局
 * NoviceLayout.create(this).highLight(button1, button2, button3);
 * create by haomingliang on 2020/3/6
 */
public class NoviceLayout extends FrameLayout {

    private Rect highLightRect;
    private Paint paint;

    private View[] highLightViews;
    private int position = 2;

    public NoviceLayout(Activity context) {
        this(context, null);
    }

    public NoviceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(0xb3006660);
        addButton(context);
        highLightRect = new Rect();
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    private void addButton(Context context) {
        Button button = new Button(context);
        button.setText("我知道了");
        this.addView(button);
        FrameLayout.LayoutParams layoutParams = (LayoutParams) button.getLayoutParams();
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;
        button.requestLayout();

        button.setOnClickListener(v -> {
            position = position + 1;
            position = position % highLightViews.length;
            this.invalidate();
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        View parent = (View) this.getParent();
        highLightViews[position].getGlobalVisibleRect(highLightRect);
        highLightRect.offset(0, -parent.getTop());
        canvas.drawRect(highLightRect, paint);
    }

    public static NoviceLayout create(Activity activity) {
        FrameLayout rootLayout = activity.findViewById(android.R.id.content);
        NoviceLayout noviceLayout = new NoviceLayout(activity);
        rootLayout.addView(noviceLayout);
        return noviceLayout;
    }

    public void highLight(View... view) {
        this.highLightViews = view;
    }

    public void dismiss() {
        ViewGroup parent = (ViewGroup) this.getParent();
        parent.removeView(this);
    }

}
