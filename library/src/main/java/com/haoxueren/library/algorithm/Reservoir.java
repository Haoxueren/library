package com.haoxueren.library.algorithm;

import java.util.Arrays;
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

    /**
     * 分布式蓄水池抽样算法
     * <p>
     * 假设有k台机器，每台机器分别进行单机版蓄水池抽样，分别抽样完毕后得到两个长度为k的数组：
     * 一个是每台机器接收的数据总量N(i)，一个是每台机器的抽样样本数组A(i)
     */
    public static void parallelSampling(int[] N, String[][] A) {
        int k = N.length; // 机器数量
        int sumN = 0; // 蓄水池的样本总量
        for (int n : N) {
            sumN += n;
        }
        // 对样本数据进行二次抽样
        String[] simples = new String[k]; // 用来保存最终采样结果
        Random random = new Random();
        int indexK = random.nextInt(sumN); // 产生一个随机数k，用于抽样一台机器
        for (int time = 0; time < k; time++) { // 进行k次再抽样
            int sumK = 0;
            for (int i = 0; i < k; i++) { // 计算k属于哪个区间
                sumK += N[i];
                if (indexK < sumK) {
                    String[] target = A[i];
                    int r = random.nextInt(target.length);
                    simples[time] = target[r];
                    return;
                }
            }
        }
        // 打印最终抽样结果
        System.out.println(Arrays.toString(simples));
    }
}
