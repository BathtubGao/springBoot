package com.bathtub.algorithm.exercise;

/**
 * 给定两组有序不重复正整数数组，第一组作为基数组，从第二组中找的到第一组数组中各元素差值和最小元素
 * 例如
 * 数组 A : 1 3 6 11 13
 * 数组 B : 2 8 10 12
 * 最小差值元素为8
 * @author 17031612
 * @date 2022/1/20
 */
public class MinDiffSum {

    public static void main(String[] args) {
        int[] ints1 = new int[]{1,4,6,12, 23, 99};
        int[] ints2 = new int[]{2,8,10,11, 17};
        System.out.println(getMinDiffSum(ints1,ints2));
    }

    private static int getMinDiffSum(int[] ints1, int[] ints2) {
        double base;
        int half = ints1.length >> 1;
        if ((ints1.length & 1) == 1) {
            base = ints1[half];
        } else {
            base = (double) (ints1[half] + ints1[half-1]) / 2;
        }
        double min = Math.abs(ints2[0] - base);
        int minValue = ints2[0];
        for (int i = 1; i < ints2.length; i++) {
            double nowMin = Math.abs(ints2[i] - base);
            if (min > nowMin) {
                min = nowMin;
                minValue = ints2[i];
            }
        }
        return minValue;
    }
}
