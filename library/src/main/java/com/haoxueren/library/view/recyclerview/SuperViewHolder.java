package com.haoxueren.library.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


/**
 * create by haomingliang on 2020/4/14
 */
public abstract class SuperViewHolder<T> extends RecyclerView.ViewHolder {

    public SuperViewHolder(ViewGroup parent, int layoutRes) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(layoutRes, parent, false));
    }

    public abstract void updateItem(T bean);
}
