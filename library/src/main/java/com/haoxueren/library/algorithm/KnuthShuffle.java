package com.haoxueren.library.algorithm;

import java.util.Random;

/**
 * create by haomingliang on 2020/2/24
 */
public class KnuthShuffle {

    /**
     * 洗牌算法(原地打乱)
     */
    public static <T> void shuffle(T[] array) {
        int length = array.length;
        Random random = new Random();
        for (int i = length - 1; i >= 0; i--) {
            int s = random.nextInt(i + 1);
            T select = array[s];
            T temp = array[i];
            array[i] = select;
            array[s] = temp;
        }
    }

}
