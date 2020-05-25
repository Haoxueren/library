package com.haoxueren.library.collection;

import com.haoxueren.library.java8.BiFunction;
import com.haoxueren.library.java8.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * List集合帮助类
 * create by haomingliang on 2020/5/25
 */
public class ListUtils {

    private ListUtils() {
    }

    /**
     * 根据指定的比较函数，取两个集合的交集
     */
    public static <T> List<T> intersect(List<T> list1, List<T> list2, BiFunction<T, T, Boolean> compare) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            T t1 = list1.get(i);
            for (int j = 0; j < list2.size(); j++) {
                T t2 = list2.get(j);
                Boolean isSatisfied = compare.apply(t1, t2);
                if (isSatisfied) {
                    list.add(t1);
                }
            }
        }
        return list;
    }

    /**
     * 根据指定的比较函数，取两个集合的交集
     */
    public static <T> Function<BiFunction<T, T, Boolean>, List<T>> intersect(List<T> list1, List<T> list2) {
        return compare -> {
            List<T> list = new ArrayList<>();
            for (int i = 0; i < list1.size(); i++) {
                T t1 = list1.get(i);
                for (int j = 0; j < list2.size(); j++) {
                    T t2 = list2.get(j);
                    Boolean isSatisfied = compare.apply(t1, t2);
                    if (isSatisfied) {
                        list.add(t1);
                    }
                }
            }
            return list;
        };
    }


}
