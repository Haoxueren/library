package com.haoxueren.library.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.haoxueren.library.R;


/**
 * 功能描述：多状态布局，包括 LOADING ERROR EMPTY SUCCESS 等状态<br>
 * CREATE BY HaoMingliang ON 2020/1/18
 */
public class StatefulLayout extends FrameLayout {

    private View loadingLayout, failureLayout, errorLayout, emptyLayout;

    public StatefulLayout(Context context) {
        this(context, null);
    }

    public StatefulLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatefulLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public StatefulLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = LayoutInflater.from(context);
        loadingLayout = inflater.inflate(R.layout.layout_loading, this, false);
        failureLayout = inflater.inflate(R.layout.layout_failure, this, false);
        errorLayout = inflater.inflate(R.layout.layout_error, this, false);
        emptyLayout = inflater.inflate(R.layout.layout_empty, this, false);
    }

    /**
     * 开始加载数据时调用
     */
    public void onLoading() {
        removeAllStateView();
        loadingLayout.setBackgroundColor(Color.WHITE);
        this.addView(loadingLayout); // 添加加载中状态布局
    }

    /**
     * 开始加载数据时调用
     *
     * @param backgroundColor 加载状态布局的背景色
     */
    public void onLoading(int backgroundColor) {
        removeAllStateView();
        loadingLayout.setBackgroundColor(backgroundColor);
        this.addView(loadingLayout); // 添加加载中状态布局
    }

    /**
     * 数据加载成功时调用
     */
    public void onSuccess() {
        removeAllStateView();
    }

    /**
     * 加载失败时调用
     */
    public void onFailure() {
        removeAllStateView();
        this.addView(failureLayout); // 添加加载错误状态的View
    }


    /**
     * 网络正常但接口异常时调用
     */
    public void onError() {
        removeAllStateView();
        this.addView(errorLayout); // 添加加载错误状态的View
    }

    /**
     * 接口返回数据为空时调用
     */
    public void onEmpty() {
        removeAllStateView();
        this.addView(emptyLayout); // 添加空状态布局
    }

    /**
     * 移除所有状态布局
     */
    private void removeAllStateView() {
        this.removeView(loadingLayout);
        this.removeView(failureLayout);
        this.removeView(errorLayout);
        this.removeView(emptyLayout);
    }
}
