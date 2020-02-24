package com.haoxueren.library.algorithm;

import java.util.Arrays;

/**
 * 参考资料：https://www.bilibili.com/video/av9830088
 */
public class Permutation {

    /**
     * 对数组进行全排列
     */
    public static void permutation(int[] array, int index) {
        if (index == array.length - 1) {
            System.out.println(Arrays.toString(array));
            return;
        }
        for (int i = index; i < array.length; i++) {
            swap(array, index, i);
            permutation(array, index + 1);
            swap(array, index, i);
        }
    }

    /**
     * 交换数组中两个元素的位置
     */
    public static void swap(int[] array, int i, int j) {
        int a = array[i];
        int b = array[j];
        array[i] = b;
        array[j] = a;
    }
}