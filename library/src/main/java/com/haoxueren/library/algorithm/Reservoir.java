package com.haoxueren.library.algorithm;

import java.util.Iterator;
import java.util.Random;

/**
 * create by haomingliang on 2020/2/24
 */
public class Reservoir {

    /**
     * 蓄水池抽样算法
     * 从list中随机抽取n个元素
     */
    public static <T> void sampling(Iterable<T> pool, T[] sample) {
        int n = sample.length;
        Random random = new Random();
        Iterator<T> iterator = pool.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            T element = iterator.next();
            // 先填满样本数组
            if (i < n) {
                sample[i] = element;
                continue;
            }
            // 按概率保留当前元素
            int keep = random.nextInt(i + 1);
            if (keep < n) {
                sample[keep] = element;
            }
        }
    }
}
