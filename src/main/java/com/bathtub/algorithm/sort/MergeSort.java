package com.bathtub.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author 17031612
 * @date 2021/12/27
 */
public class MergeSort {

    private static int[] arr = {16, 7, 3, 20, 21, 3, 1, 33, 9, 2, 6};

    private static int[] leftArr = new int[arr.length >> 1];

    public static void sort(int begin, int end) {
        if (end-begin <= 1) {
            return;
        }
        int mid = (end + begin) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private static void merge(int begin,int mid, int end) {
        int leftB = begin, rightB = mid;
        int index = 0;
        for (int i = 0; i < mid-begin; i++) {
            leftArr[i] = arr[begin+i];
        }
        while(index < mid-begin) {
            if (rightB >= end || leftArr[index] < arr[rightB]) {
                arr[leftB++] = leftArr[index++];
            } else {
                arr[leftB++] = arr[rightB++];
            }
        }
    }

    public static void main(String[] args) {
        sort(0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
