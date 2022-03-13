package com.bathtub.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author 17031612
 * @date 2021/12/27
 */
public class InsertSort {
    private static int[] arr = {16, 7, 3, 20, 21, 3, 1, 33, 9, 2, 6};

    public static void sort1() {
        for (int begin = 1; begin < arr.length; begin++) {
            int cur = begin;
            int curValue = arr[cur];
            while (cur > 0 && curValue < arr[cur-1]) {
                arr[cur] = arr[--cur];
            }
            arr[cur] = curValue;
        }
    }

    /**
     * 基于二分查找定位
     * @author 17031612
     * @date 2021/12/27
     */
    public static void sort2() {
        for (int begin = 1; begin < arr.length; begin++) {
            int cur = begin;
            int curValue = arr[cur];
            int index = bSearch(0, begin, curValue);
            while (cur > index) {
                arr[cur] = arr[--cur];
            }
            arr[index] = curValue;
        }
    }

    /**
     * 二分查找
     */
    private static int bSearch(int begin, int end, int val) {
        if (begin == end) {
            return begin;
        }
        int mid = (begin + end) >> 1;
        if (arr[mid] > val) {
            return bSearch(begin, mid, val);
        } else {
            return bSearch(mid+1, end, val);
        }
    }

    public static void main(String[] args) {
        sort2();
        System.out.println(Arrays.toString(arr));
    }
}
