package com.haoxueren.library.view.recyclerview;

import android.view.ViewGroup;

/**
 * 列表没有数据时，显示空状态布局
 * create by haomingliang on 2020/4/23
 */
public class EmptyViewHolder extends SuperViewHolder {

    public EmptyViewHolder(ViewGroup parent, int itemLayout) {
        super(parent, itemLayout);
    }

    @Override
    public void updateItem(Object bean) {

    }
}
