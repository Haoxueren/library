package com.haoxueren.library.view.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.haoxueren.library.java8.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * 支持空数据状态
 * create by haomingliang on 2020/4/14
 */
public class SuperListAdapter<T> extends RecyclerView.Adapter<SuperViewHolder> {

    private static final int ITEM_EMPTY = -1;

    private int emptyLayout;

    private Function<ViewGroup, SuperViewHolder> holderFunction;

    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    public void createViewHolder(Function<ViewGroup, SuperViewHolder> function) {
        this.holderFunction = function;
    }

    public void setEmptyLayout(int emptyLayout) {
        this.emptyLayout = emptyLayout;
    }

    @NonNull
    @Override
    public SuperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_EMPTY) {
            return new EmptyViewHolder(parent,emptyLayout);
        }
        return holderFunction.apply(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == ITEM_EMPTY) {
            holder.updateItem(null);
        } else {
            T bean = list.get(position);
            holder.updateItem(bean);
        }

    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 1 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.isEmpty()) {
            return ITEM_EMPTY;
        }
        return super.getItemViewType(position);
    }
}
