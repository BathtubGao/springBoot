package com.bathtub.algorithm.exercise;

import java.util.Arrays;

/**
 * 给的一个非递减顺序排序的整数数组，返回每个数字平方所组成的数组。要求也按非递减顺序排序
 */
public class OrderlySquare {
    public static void main(String[] args) {
        int[] ints = new int[]{-7,-6,-3, 0, 2 ,3 , 5,11};
        order(ints);
        System.out.println(Arrays.toString(ints));
    }
    private static void order(int[] ints) {
        for(int i = 0; i < ints.length; i++) {
            ints[i] = ints[i]*ints[i];
        }
        int end = ints.length-1;
        while(end > 0) {
            if (ints[0] > ints[end]) {
                int temp = ints[0];
                ints[0] = ints[end];
                ints[end] = temp;
                if (ints[0] < ints[1] && end != 1) {
                    temp = ints[0];
                    ints[0] = ints[1];
                    ints[1] = temp;
                }
                end--;
            } else {
                end--;
            }

        }
    }
}
