package com.haoxueren.library.ui;


import com.haoxueren.library.java8.Action;
import com.haoxueren.library.java8.Consumer;

import java.util.List;

/**
 * 下拉刷新，上拉加载的逻辑框架
 * create by haomingliang on 2020/4/22
 */
public class LoadMoreHelper<T> {

    private List<T> data; // 列表数据集合

    private int page, size; // 要加载的页数

    private boolean hasMoreData = true; // 是否还有更多数据

    public LoadMoreHelper(List<T> list, int page, int size) {
        this.page = page;
        this.size = size;
        this.data = list;
    }

    /**
     * 下拉刷新时调用
     */
    public void onRefresh(Consumer<Integer> request) {
        request.accept(1);
    }

    /**
     * 上拉加载时调用
     * 有更多数据时请求服务器，无更多数据时完成加载
     */
    public void onLoadMore(Consumer<Integer> request, Action noMoreAction) {
        if (hasMoreData) {
            int tempPage = page + 1;
            request.accept(tempPage);
        } else {
            noMoreAction.run();
        }
    }

    /**
     * 数据加载成功后调用，需要指明是否为上拉加载
     */
    public void onSuccess(List<T> newData, boolean isLoadMore, Consumer<List<T>> result) {
        if (isLoadMore) {
            this.onLoadMoreSuccess(newData, result);
        } else {
            this.onRefreshSuccess(newData, result);
        }
    }

    /**
     * 下拉刷新成功后调用
     */
    private void onRefreshSuccess(List<T> newData, Consumer<List<T>> result) {
        hasMoreData = newData.size() == size; // 是否有更多数据
        page = 1;
        data.clear();
        data.addAll(newData);
        result.accept(data);
    }

    /**
     * 上拉加载成功时调用
     */
    private void onLoadMoreSuccess(List<T> newData, Consumer<List<T>> result) {
        hasMoreData = newData.size() == size; // 是否有更多数据
        page = newData.isEmpty() ? page : page + 1; // 页码加 1
        data.addAll(newData);
        result.accept(data);
    }

}
