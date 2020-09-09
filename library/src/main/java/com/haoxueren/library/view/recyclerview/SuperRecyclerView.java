package com.haoxueren.library.view.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.haoxueren.library.R;
import com.haoxueren.library.java8.Function;

import java.util.List;

/**
 * RecyclerView 封装
 * 默认为 LinearLayoutManager
 * create by haomingliang on 2020/4/14
 */
public class SuperRecyclerView extends RecyclerView {

    private SuperListAdapter adapter;

    public SuperRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public SuperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperRecyclerView);
        int emptyLayout = typedArray.getResourceId(R.styleable.SuperRecyclerView_emptyLayout, R.layout.item_empty);
        typedArray.recycle();
        // 若未通过XML配置LayoutManager，则设置默认的LinearLayoutManager
        LayoutManager layoutManager = this.getLayoutManager();
        if (layoutManager == null) {
            this.setLayoutManager(new LinearLayoutManager(context));
        }
        adapter = new SuperListAdapter();
        adapter.setEmptyLayout(emptyLayout);
        this.setAdapter(adapter);
    }

    public void initView(Function<ViewGroup, SuperViewHolder> function) {
        adapter.createViewHolder(function);
    }

    public <T> List<T> getList() {
        return adapter.getList();
    }

    public <T> void initData(List<T> list) {
        // 使用view.post()延时调用，解决Cannot call this method while RecyclerView is computing a layout or scrolling
        adapter.setList(list);
        this.post(() -> adapter.notifyDataSetChanged());
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return adapter.getList().isEmpty();
    }
}
